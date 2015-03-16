package org.oyach.mybatis.datasource;

import org.oyach.mybatis.aop.UseDataSourceMetaData;
import org.oyach.mybatis.aop.UseDataSourceSupport;
import org.springframework.core.NamedThreadLocal;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;
import org.springframework.jdbc.datasource.lookup.MapDataSourceLookup;
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
     * 确定当前的dataSourceKey
     *
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        String name = DataSourcePartitionManager.getCurrentDataSourceName();
        String type = DataSourcePartitionManager.getCurrentDataSourceType();
        if (name != null && type != null){
            return name + "#" + type;
        }

        if (type != null){
            return "default#" + type;
        }

        return null;
    }

}
