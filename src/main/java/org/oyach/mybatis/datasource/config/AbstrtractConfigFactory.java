package org.oyach.mybatis.datasource.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * 支持多数据区数据库配置
 *
 * @author liuzhenyuan
 * @version Last modified 15/3/11
 * @since 0.0.1
 */
public abstract class AbstrtractConfigFactory implements ConfigFactory,BeanDefinitionRegistryPostProcessor, InitializingBean, ApplicationContextAware, BeanNameAware {

    protected DataSourceProperty defaultDataSourceProperty;
    /**
     * 数据连接数据
     */
    protected Map<String, DataSourceProperty> dataSourcePartitions;

    protected String beanName;
    protected ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    public Map<String, DataSourceProperty> getDataSourcePartitions() {
        return dataSourcePartitions;
    }

    public void setDataSourcePartitions(Map<String, DataSourceProperty> dataSourcePartitions) {
        this.dataSourcePartitions = dataSourcePartitions;
    }

    public DataSourceProperty getDefaultDataSourceProperty() {
        return defaultDataSourceProperty;
    }

    public void setDefaultDataSourceProperty(DataSourceProperty defaultDataSourceProperty) {
        this.defaultDataSourceProperty = defaultDataSourceProperty;
    }
}
