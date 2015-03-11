package org.oyach.mybatis.datasource;

import org.oyach.mybatis.datasource.config.ConfigFactory;

/**
 * 针对动态数据源进行扩展，方便进行配置
 *
 * @author liuzhenyuan
 * @version Last modified 15/3/10
 * @since 0.0.1
 */
public class AutoDynamicDataSource extends DynamicDataSource{

    private ConfigFactory configFactory;

    public ConfigFactory getConfigFactory() {
        return configFactory;
    }

    public void setConfigFactory(ConfigFactory configFactory) {
        this.configFactory = configFactory;
        // TODO 获取数据源  已经构造完整
    }
}
