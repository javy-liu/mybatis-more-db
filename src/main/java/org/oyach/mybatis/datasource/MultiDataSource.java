package org.oyach.mybatis.datasource;

import org.springframework.jdbc.datasource.AbstractDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数据源
 * 链接多个DataSource，具体实现交予链接的实现 本类不能获取connection
 *
 * @author liuzhenyuan
 * @version Last modified 15/3/6
 * @since 0.0.1
 */
public class MultiDataSource extends AbstractDataSource {
    private Map<Object, DataSource> multiDataSource = new HashMap<>();

    @Override
    public Connection getConnection() throws SQLException {
        throw new SQLException("MultiDataSource can't get connection");
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        throw new SQLException("MultiDataSource can't get connection");
    }

    public DataSource getDataSource(Object key){
        return multiDataSource.get(key);
    }

    public void addDataSource(Object key, DataSource dataSource){
        multiDataSource.put(key, dataSource);
    }
}
