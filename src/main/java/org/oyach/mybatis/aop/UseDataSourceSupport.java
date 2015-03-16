package org.oyach.mybatis.aop;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.oyach.mybatis.annotation.UseDataSource;
import org.oyach.mybatis.datasource.DataSourcePartitionManager;
import org.oyach.mybatis.datasource.DataSourceType;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 支持UseDataSource注解
 *
 * @author liuzhenyuan
 * @version Last modified 15/3/11
 * @since 0.0.1
 */
public class UseDataSourceSupport implements MethodInterceptor {
    /**
     * 拦截事务的通知
     */
    private TransactionInterceptor advice;


    /**
     * 处理@UseDataSource注解逻辑，如果没有该标签进行默认处理
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        UseDataSourceMetaData useDataSourceMetaData = new UseDataSourceMetaData();

        Method method = invocation.getMethod();
        Class clazz = invocation.getThis().getClass();

        Boolean readOnly = readOnly(method, clazz);

        UseDataSource useDataSource = AnnotationUtils.findAnnotation(method, UseDataSource.class);

        if (useDataSource == null || useDataSource.dataSource().length == 0){
            /** 采用默认策略 */
            DataSourcePartitionManager.setCurrentUseDataSourceMetaData(null);
            String type = readOnly == null ? null : readOnly ? "read" : "write";
            DataSourcePartitionManager.setCurrentDataSourceType(type);
            return invocation.proceed();
        }

        UseDataSource.DataSource[] dataSources = useDataSource.dataSource();

        List<DataSourceType> dataSourceTypes = new ArrayList<>();

        for (UseDataSource.DataSource dataSource : dataSources){
            DataSourceType dataSourceType = new DataSourceType();
            dataSourceType.setName(dataSource.name());
            String type = dataSource.type();
            if (StringUtils.isEmpty(type)){
                dataSourceType.setType(readOnly == null ? null : readOnly ? "read" : "write");
            } else {
                dataSourceType.setType(type);
            }
            dataSourceTypes.add(dataSourceType);
        }
        useDataSourceMetaData.setDataSourceTypes(dataSourceTypes);
        DataSourcePartitionManager.setCurrentUseDataSourceMetaData(useDataSourceMetaData);
        return invocation.proceed();
    }


    private Boolean readOnly(Method method, Class clazz){
        Boolean readOnly;
        /** 从aop中获取事务 */
        TransactionAttribute transactionAttribute = advice.getTransactionAttributeSource().getTransactionAttribute(method, clazz);

        /** 从annotation中获取 */
        Transactional transactional = AnnotationUtils.getAnnotation(method, Transactional.class);

        Transactional classTransactional = AnnotationUtils.findAnnotation(clazz, Transactional.class);

        /** annotation > aop > default */
        if (classTransactional != null || transactional != null){
            readOnly = classTransactional != null ? classTransactional.readOnly() : transactional.readOnly();
        } else if (transactionAttribute != null){
            readOnly = transactionAttribute.isReadOnly();
        } else {
            /** 没有事务 */
            readOnly = null;
        }

        return readOnly;
    }

    public TransactionInterceptor getAdvice() {
        return advice;
    }

    public void setAdvice(TransactionInterceptor advice) {
        this.advice = advice;
    }
}
