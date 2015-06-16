package org.oyach.mybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.oyach.mybatis.spring.AnimalFactory;
import org.oyach.mybatis.spring.AnimalFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author liuzhenyuan
 * @version Last modified 15/3/6
 * @since 0.0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:applicationContext-test.xml"})
@DirtiesContext
public class AppTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AnimalFactoryBean animalFactoryBean;

    @Autowired
    private AnimalFactory animalFactory;

    @Test
    public void test01() throws Exception {
        System.out.println();

    }
}
