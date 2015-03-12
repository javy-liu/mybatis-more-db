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
     * 指定使用什么样的数据源
     *
     * @return
     */
    String type();

    /**
     * 指定使用哪些数据源
     *
     * @return
     */
    String[] dataSource() default {};

}
