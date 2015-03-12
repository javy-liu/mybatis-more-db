package org.oyach.mybatis.datasource;

import org.springframework.jdbc.datasource.AbstractDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据区
 * 多个数据源
 *
 * @author liuzhenyuan
 * @version Last modified 15/3/10
 * @since 0.0.1
 */
public class DataSourcePartition extends AbstractDataSource {
    private Map<Object, DataSource> dataSourcePartition = new HashMap<>();

    @Override
    public Connection getConnection() throws SQLException {
        throw new SQLException("MultiDataSource can't get connection");
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        throw new SQLException("MultiDataSource can't get connection");
    }

    public DataSource getDataSource(Object key){
        return dataSourcePartition.get(key);
    }

    public void addDataSource(Object key, DataSource dataSource){
        dataSourcePartition.put(key, dataSource);
    }
}
