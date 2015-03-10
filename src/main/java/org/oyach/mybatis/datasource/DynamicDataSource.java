package org.oyach.mybatis.datasource;

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
    private static final ThreadLocal<DataSourceKey> dataSourceKey = new NamedThreadLocal<>("dataSource key");

    /**
    /**
     * 获取当前线程中使用的DataSource
     *
     * @return
     */
    @Override
    protected DataSource determineTargetDataSource() {
        DataSourceKey lookupKey = dataSourceKey.get();
        DataSource dataSource = super.determineTargetDataSource();

        DataSourceType type = lookupKey.getDataSourceType();
        if (dataSource instanceof MultiDataSource) {
            /** 多数据源处理 */
            MultiDataSource multiDataSource = (MultiDataSource) dataSource;
            DataSource ds = multiDataSource.getDataSource(type);

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
            MultiDataSource multiDataSource = new MultiDataSource();
            for (Object key : ((Map) dataSource).keySet()) {
                multiDataSource.addDataSource(key, multiDataSource);
            }
            return multiDataSource;
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
        DataSourceKey key = dataSourceKey.get();

        /** 采用默认策略 */
        if (key == null) return null;

        if (readOnly) {
            key.setDataSourceType(DataSourceType.READ);
        } else {
            key.setDataSourceType(DataSourceType.WRITE);
        }
        // TODO 后续接口 支撑 慢读 等扩展


        return key.getName();
    }


    /**
     * 依靠该键值取数据源
     */
    public static class DataSourceKey {
        private String name;
        private DataSourceType dataSourceType;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public DataSourceType getDataSourceType() {
            return dataSourceType;
        }

        public void setDataSourceType(DataSourceType dataSourceType) {
            this.dataSourceType = dataSourceType;
        }
    }
}
