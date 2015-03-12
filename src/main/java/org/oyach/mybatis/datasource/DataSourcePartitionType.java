package org.oyach.mybatis.datasource;

/**
 * 标识使用数据区类型
 *
 * @author liuzhenyuan
 * @version Last modified 15/3/6
 * @since 0.0.1
 */
public class DataSourcePartitionType {

    public static final String NAME_AND_TYPE_SPLIT = "#";

    /**
     * 这个数据区的名字
     */
    private String name;

    /**
     * 这个数据区类型
     */
    private String type;

    public DataSourcePartitionType() {
    }

    public DataSourcePartitionType(String nameAndType) {
        String[] arr = nameAndType.split(NAME_AND_TYPE_SPLIT);
        this.name = arr[0];
        this.type = arr[1];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataSourcePartitionType)) return false;

        DataSourcePartitionType that = (DataSourcePartitionType) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name + NAME_AND_TYPE_SPLIT + type;
    }
}
