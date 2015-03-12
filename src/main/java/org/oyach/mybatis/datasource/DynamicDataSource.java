package org.oyach.mybatis.datasource;

import org.oyach.mybatis.datasource.config.DataSourcePartitionConfigFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 动态数据源
 *
 * @author liuzhenyuan
 * @version Last modified 15/3/6
 * @since 0.0.1
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 记录当前线程选择哪个库
     */
    private static final NamedThreadLocal<DataSourcePartitionType> dataSourcePartitionType = new NamedThreadLocal<>("dataSource partition type");

    /**
    /**
     * 获取当前线程中使用的DataSource
     *
     * @return
     */
    @Override
    protected DataSource determineTargetDataSource() {

        DataSource dataSource = super.determineTargetDataSource();
        DataSourcePartitionType lookupKey = dataSourcePartitionType.get();
        String type = null;
        if (lookupKey != null){
            type = lookupKey.getType();
        }
        if (dataSource instanceof DataSourcePartition) {
            /** 多数据源处理 */
            DataSourcePartition dataSourcePartition = (DataSourcePartition) dataSource;
            DataSource ds = dataSourcePartition.getDataSource(type);

            if (ds == null) {
                throw new IllegalStateException("Cannot determine target DataSource for lookup key [" + lookupKey + "] type [" + type + "]");
            }

            return ds;
        }

        return dataSource;
    }

    /**
     * 获取 数据源
     *
     * @param dataSource
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    protected DataSource resolveSpecifiedDataSource(Object dataSource) throws IllegalArgumentException {
        if (dataSource instanceof Map) {
            DataSourcePartition dataSourcePartition = new DataSourcePartition();
            for (Object key : ((Map) dataSource).keySet()) {
                DataSourcePartitionType type = new DataSourcePartitionType((String) key);
                dataSourcePartition.addDataSource(type.getType(), (DataSource) ((Map) dataSource).get(key));
            }
            return dataSourcePartition;
        }
        return super.resolveSpecifiedDataSource(dataSource);
    }


    /**
     * 确定当前的dataSourceKey
     *
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        // TODO 首先确定type 最好开放接口 方便扩展
        /** 根据事务进行确定 */
        // 事务能吧读写分开 但是不能知道慢读  所以这里要采用2种策略
        boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        DataSourcePartitionType type = dataSourcePartitionType.get();
        /** 采用默认策略 */
        if (type == null) type = new DataSourcePartitionType();

        if (readOnly) {
            type.setType("read");
        } else {
            type.setType("write");
        }
        // TODO 后续接口 支撑 慢读 等扩展

        dataSourcePartitionType.set(type);
        return type;
    }


    @Override
    protected Object resolveSpecifiedLookupKey(Object lookupKey) {
        if (lookupKey instanceof DataSourcePartitionType){
            DataSourcePartitionType type = (DataSourcePartitionType) lookupKey;
            return type.getName();
        }
        return super.resolveSpecifiedLookupKey(lookupKey);
    }

}
