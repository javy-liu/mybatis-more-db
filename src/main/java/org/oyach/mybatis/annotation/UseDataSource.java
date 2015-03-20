package org.oyach.mybatis.annotation;

import java.lang.annotation.*;

/**
 * 标示强制使用指定数据源
 * 注意使用了本标签代表自动选择将不会起作用了
 *
 * @author liuzhenyuan
 * @version Last modified 15/3/6
 * @since 0.0.1
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface UseDataSource {

    /**
     * 指定使用的数据区
     *
     * @return
     */
    DataSource[] dataSource() default {};

    /**
     * 散表维度
     *
     * @return
     */
    ShardBy[] shardBy() default {};


    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    @interface DataSource {

        String name() default "";

        String type() default "";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    @interface ShardBy {

        String[] name() default {};

        String[] type() default {};
    }
}
