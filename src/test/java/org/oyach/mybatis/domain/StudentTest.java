package org.oyach.mybatis.domain;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.core.NamedThreadLocal;

/**
 * @author liuzhenyuan
 * @version Last modified 15/3/12
 * @since 0.0.1
 */
public class StudentTest {

    private NamedThreadLocal<String> stringNamedThreadLocal = new NamedThreadLocal<>("name");
    @Test
    public void test01() throws Exception {
        Student student1 = new Student();
        student1.setId(3L);

        Student student2 = new Student();
        student2.setId(4L);
        student2.setName("oaych");


        BeanUtils.copyProperties(student1, student2);

        System.out.println(student2);
    }

    @Test
    public void test02() throws Exception {
        stringNamedThreadLocal.set("11111");
        String v = stringNamedThreadLocal.get();

        System.out.println(v);
        aa();
        String vv = stringNamedThreadLocal.get();
        System.out.println(vv);
    }

    private void aa(){
        String vv = stringNamedThreadLocal.get();
        System.out.println(vv);

    }
}
