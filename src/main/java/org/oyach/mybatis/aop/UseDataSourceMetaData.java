package org.oyach.mybatis.aop;

import org.oyach.mybatis.datasource.DataSourceType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzhenyuan
 * @version Last modified 15/3/13
 * @since 0.0.1
 */
public class UseDataSourceMetaData {
    private List<DataSourceType> dataSourceTypes = new ArrayList<>();

    public UseDataSourceMetaData() {
    }

    public List<DataSourceType> getDataSourceTypes() {
        return dataSourceTypes;
    }

    public void setDataSourceTypes(List<DataSourceType> dataSourceTypes) {
        this.dataSourceTypes = dataSourceTypes;
    }
}
