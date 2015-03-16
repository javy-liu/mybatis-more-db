package org.oyach.mybatis.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.oyach.mybatis.aop.UseDataSourceMetaData;
import org.oyach.mybatis.aop.UseDataSourceSupport;
import org.oyach.mybatis.datasource.DataSourcePartitionManager;
import org.oyach.mybatis.datasource.DataSourceType;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * 针对Mybatis进行扩展，用来支撑分库分表的插件
 *
 * @author liuzhenyuan
 * @version Last modified 15/3/6
 * @since 0.0.1
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class MoreDataBasePlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        UseDataSourceMetaData useDataSourceMetaData = DataSourcePartitionManager.getCurrentUseDataSourceMetaData();

        if (useDataSourceMetaData == null){
            return invocation.proceed();
        }

        List<DataSourceType> dataSourceTypes = useDataSourceMetaData.getDataSourceTypes();

        Object obj = null;
        for (DataSourceType dataSourceType : dataSourceTypes){
            DataSourcePartitionManager.setCurrentDataSourceName(dataSourceType.getName());
            DataSourcePartitionManager.setCurrentDataSourceType(dataSourceType.getType());
            /** 合并结果 */
            obj = mergeResult(invocation, obj);
        }
        return obj;
    }

    /**
     * 合并结果
     *
     * @param invocation
     * @param object
     * @return
     * @throws Throwable
     */
    private Object mergeResult(Invocation invocation, Object object) throws Throwable {
        Object result = invocation.proceed();
        if ((result instanceof List) && (object == null)){
            return result;
        } else if ((result instanceof List) && (object instanceof List)){
            return ((List) object).addAll((Collection) result);
        }
        return object;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("===========setProperties============");
    }
}
