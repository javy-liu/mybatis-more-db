package org.oyach.mybatis.plugin;

import com.google.common.base.Joiner;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Invocation;
import org.oyach.mybatis.aop.UseDataSourceMetaData;
import org.oyach.mybatis.datasource.DataSourcePartitionManager;
import org.oyach.mybatis.datasource.DataSourceType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

/**
 * 针对Invocation进行扩展
 *
 * @author liuzhenyuan
 * @version Last modified 15/3/19
 * @since 0.0.1
 */
public class InvocationWrapper {

    /** 反射参数 */
    private Object[] args;

    private Invocation invocation;

    private UseDataSourceMetaData useDataSourceMetaData;

    public InvocationWrapper(UseDataSourceMetaData useDataSourceMetaData, Invocation invocation) {
        this.invocation = invocation;
        this.useDataSourceMetaData = useDataSourceMetaData;
    }


    /**
     * 获取结果
     *
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        List<DataSourceType> dataSourceTypes = useDataSourceMetaData.getDataSourceTypes();

        Object obj = null;
        for (DataSourceType dataSourceType : dataSourceTypes){
            DataSourcePartitionManager.setCurrentDataSourceName(dataSourceType.getName());
            DataSourcePartitionManager.setCurrentDataSourceType(dataSourceType.getType());
            try {
                /** 合并结果 */
                obj = mergeResult(invocation);
            } catch (Throwable throwable) {
                throw new InvocationTargetException(throwable);
            }
        }
        return invocation.getMethod().invoke(invocation.getTarget(), invocation.getArgs());
    }

    /**
     * 合并结果
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    private Object mergeResult(Invocation invocation) throws Throwable {
        Method method = invocation.getMethod();

        return null;
    }

    /**
     * 仿制新的mappedStatement
     *
     * @param mappedStatement
     * @param sqlSource
     */
    private MappedStatement buildMappedStatement(MappedStatement mappedStatement, SqlSource sqlSource){
        MappedStatement.Builder statementBuilder = new MappedStatement.Builder(mappedStatement.getConfiguration(),
                mappedStatement.getId(), sqlSource, mappedStatement.getSqlCommandType());
        statementBuilder.resource(mappedStatement.getResource());
        statementBuilder.fetchSize(mappedStatement.getFetchSize());
        statementBuilder.statementType(mappedStatement.getStatementType());
        statementBuilder.keyGenerator(mappedStatement.getKeyGenerator());
        if (mappedStatement.getKeyProperties() != null){
            statementBuilder.keyProperty(Joiner.on(",").join(mappedStatement.getKeyProperties()));
        }
        if (mappedStatement.getKeyColumns() != null){
            statementBuilder.keyColumn(Joiner.on(",").join(mappedStatement.getKeyColumns()));
        }
        if (mappedStatement.getResulSets() != null){
            statementBuilder.resulSets(Joiner.on(",").join(mappedStatement.getResulSets()));
        }
        statementBuilder.databaseId(mappedStatement.getDatabaseId());
        statementBuilder.lang(mappedStatement.getLang());
        statementBuilder.resultOrdered(mappedStatement.isResultOrdered());


        statementBuilder.timeout(mappedStatement.getTimeout());
        statementBuilder.parameterMap(mappedStatement.getParameterMap());
        statementBuilder.resultMaps(mappedStatement.getResultMaps());
        statementBuilder.resultSetType(mappedStatement.getResultSetType());
        statementBuilder.flushCacheRequired(mappedStatement.isFlushCacheRequired());
        statementBuilder.useCache(mappedStatement.isUseCache());
        statementBuilder.cache(mappedStatement.getCache());

        return statementBuilder.build();
    }
}
