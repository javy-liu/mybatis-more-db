package org.oyach.mybatis.datasource;

import org.springframework.jdbc.datasource.lookup.MapDataSourceLookup;

/**
 * 数据区
 * 多个数据源
 *
 * @author liuzhenyuan
 * @version Last modified 15/3/10
 * @since 0.0.1
 */
public class PartitionLookup extends MapDataSourceLookup {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
