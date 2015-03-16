package org.oyach.mybatis.datasource.config;

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
