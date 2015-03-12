package org.oyach.mybatis.datasource.config;

import org.oyach.mybatis.datasource.DataSourcePartition;
import org.oyach.mybatis.datasource.DataSourcePartitionType;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * 快速配置数据源接口
 *
 * @author liuzhenyuan
 * @version Last modified 15/3/11
 * @since 0.0.1
 */
public interface ConfigFactory {

    /**
     * 构建出数据区
     *
     * @return
     */
    Map<Object, Object> buildDataSourcePartitions();


}
