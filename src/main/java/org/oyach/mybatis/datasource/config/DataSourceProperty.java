package org.oyach.mybatis.datasource.config;

/**
 * @author liuzhenyuan
 * @version Last modified 15/3/12
 * @since 0.0.1
 */
public class DataSourceProperty {
    /**
     * 数据库驱动
     */
    private String driverClassName;
    /**
     * 数据库地址
     */
    private String jdbcUrl;
    /**
     * 访问用户
     */
    private String user;
    /**
     * 密码
     */
    private String password;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
