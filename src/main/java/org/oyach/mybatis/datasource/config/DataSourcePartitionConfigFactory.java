package org.oyach.mybatis.datasource.config;

import org.oyach.mybatis.datasource.DataSourceType;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * DataSource配置实现
 * @author liuzhenyuan
 * @version Last modified 15/3/12
 * @since 0.0.1
 */
public class DataSourcePartitionConfigFactory extends AbstrtractConfigFactory {

    private List<String> beanNames = new ArrayList<>();

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        DataSourceProperty defaultDS = getDefaultDataSourceProperty();
        Map<String, DataSourceProperty> dataSourcePropertyMap = getDataSourcePartitions();

        for (Map.Entry<String, DataSourceProperty> entry : dataSourcePropertyMap.entrySet()){
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(DriverManagerDataSource.class);
            DataSourceProperty dataSourceProperty = mergeDataSourceProperty(defaultDS, entry.getValue());
            builder.addPropertyValue("driverClassName", dataSourceProperty.getDriverClassName());
            builder.addPropertyValue("url", dataSourceProperty.getJdbcUrl());
            builder.addPropertyValue("username", dataSourceProperty.getUser());
            builder.addPropertyValue("password", dataSourceProperty.getPassword());
            registry.registerBeanDefinition(entry.getKey(), builder.getBeanDefinition());

            beanNames.add(entry.getKey());
        }
    }

    /**
     * 构造全属性对象
     *
     * @param defaultDataSourceProperty
     * @param dataSourceProperty
     * @return
     */
    private DataSourceProperty mergeDataSourceProperty(DataSourceProperty defaultDataSourceProperty, DataSourceProperty dataSourceProperty){
        dataSourceProperty.setDriverClassName(StringUtils.isEmpty(dataSourceProperty.getDriverClassName()) ?
                defaultDataSourceProperty.getDriverClassName() : dataSourceProperty.getDriverClassName());

        dataSourceProperty.setJdbcUrl(StringUtils.isEmpty(dataSourceProperty.getJdbcUrl()) ?
                defaultDataSourceProperty.getJdbcUrl() : dataSourceProperty.getJdbcUrl());

        dataSourceProperty.setUser(StringUtils.isEmpty(dataSourceProperty.getUser()) ?
                defaultDataSourceProperty.getUser() : dataSourceProperty.getUser());

        dataSourceProperty.setPassword(StringUtils.isEmpty(dataSourceProperty.getPassword()) ?
                defaultDataSourceProperty.getPassword() : dataSourceProperty.getPassword());

        return dataSourceProperty;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public Map<Object, Object> buildDataSourcePartitions() {
        Map<Object, Object> dataSourcePartitionMap = new LinkedHashMap<>();
        for (String beanName : beanNames){
            DriverManagerDataSource driverManagerDataSource = applicationContext.getBean(beanName, DriverManagerDataSource.class);
            dataSourcePartitionMap.put(beanName, driverManagerDataSource);
        }
        return dataSourcePartitionMap;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
