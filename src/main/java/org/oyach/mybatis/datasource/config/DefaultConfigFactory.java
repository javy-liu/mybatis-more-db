package org.oyach.mybatis.datasource.config;

import org.oyach.mybatis.datasource.DataSourcePartitionType;

/**
 * 默认实现的数据库配置
 *
 * @author liuzhenyuan
 * @version Last modified 15/3/11
 * @since 0.0.1
 */
public class DefaultConfigFactory {
    /** 数据库驱动 */
    private String driverClassName;
    /** 数据库地址 */
    private String jdbcUrl;
    /** 访问用户 */
    private String user;
    /** 密码 */
    private String password;
    /** 数据区数量 */
    private int partitionNum;
    /** 数据区类型 */
    private DataSourcePartitionType dataSourcePartitionType;

}
