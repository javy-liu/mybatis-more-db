package org.oyach.mybatis.datasource.config;

import org.oyach.mybatis.datasource.DataSourceType;
import org.springframework.context.ApplicationContext;

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
    private DataSourceType dataSourceType;




    public int getPartitionNum() {
        return partitionNum;
    }

    public void setPartitionNum(int partitionNum) {
        this.partitionNum = partitionNum;
    }
}
