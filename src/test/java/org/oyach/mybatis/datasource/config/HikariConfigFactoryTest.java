package org.oyach.mybatis.datasource.config;

import org.junit.Test;
import org.oyach.mybatis.AbstractAppTest;
import org.oyach.mybatis.datasource.AutoDynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class HikariConfigFactoryTest extends AbstractAppTest {


    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AutoDynamicDataSource dynamicDataSource;

    @Test
    public void testHikariConfigFactory() throws Exception {

        System.out.println(applicationContext);
    }
}