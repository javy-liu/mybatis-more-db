package org.oyach.mybatis.datasource;

import org.oyach.mybatis.aop.UseDataSourceMetaData;
import org.springframework.core.NamedThreadLocal;

/**
 * 记录当前线程方法中数据选择
 *
 * @author liuzhenyuan
 * @version Last modified 15/3/13
 * @since 0.0.1
 */
public abstract class DataSourcePartitionManager {
    /**
     * 记录当前线程选择哪个库
     */
    private static final ThreadLocal<String> currentDataSourceName = new NamedThreadLocal<>("current dataSource name");

    /**
     * 区域库记录类型
     */
    private static final ThreadLocal<String> currentDataSourceType = new NamedThreadLocal<>("current dataSource type");

    /**
     * 记录当前执行信息
     */
    private static final ThreadLocal<UseDataSourceMetaData> currentUseDataSourceMetaData =
            new NamedThreadLocal<>("Current UseDataSourceMetaData");


    public static String getCurrentDataSourceName() {
        return currentDataSourceName.get();
    }

    public static String getCurrentDataSourceType() {
        return currentDataSourceType.get();
    }

    public static UseDataSourceMetaData getCurrentUseDataSourceMetaData() {
        return currentUseDataSourceMetaData.get();
    }

    public static void setCurrentDataSourceName(String dataSourceName) {
        currentDataSourceName.set(dataSourceName);
    }

    public static void setCurrentDataSourceType(String dataSourceType) {
        currentDataSourceType.set(dataSourceType);
    }

    public static void setCurrentUseDataSourceMetaData(UseDataSourceMetaData useDataSourceMetaData) {
        currentUseDataSourceMetaData.set(useDataSourceMetaData);
    }
}
