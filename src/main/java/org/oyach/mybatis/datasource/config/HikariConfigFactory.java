package org.oyach.mybatis.datasource.config;

import com.zaxxer.hikari.HikariConfig;
import org.oyach.mybatis.datasource.DataSourcePartition;
import org.oyach.mybatis.datasource.DataSourcePartitionType;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;

/**
 * Hikari数据连接池的配置实现
 *
 * @author liuzhenyuan
 * @version Last modified 15/3/11
 * @since 0.0.1
 */
public class HikariConfigFactory  {

    public static final String BEAN_NAME_ADDTION = "_";

    private ApplicationContext applicationContext;

    private String beanName;

    /**
     * 数据区数量
     */
    private int partitionNum;

    /**
     * 数据区类型
     */
    private DataSourcePartitionType dataSourcePartitionType;




    public int getPartitionNum() {
        return partitionNum;
    }

    public void setPartitionNum(int partitionNum) {
        this.partitionNum = partitionNum;
    }
}
