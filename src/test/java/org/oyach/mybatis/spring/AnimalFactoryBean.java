package org.oyach.mybatis.spring;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author liuzhenyuan
 * @version Last modified 15/6/16
 * @since 0.0.1
 */
public class AnimalFactoryBean implements FactoryBean<AnimalFactory>, InitializingBean, ApplicationListener<ApplicationEvent> {

    private String factoryName;

    private int size;

    private AnimalFactory animalFactory;

    @Override
    public AnimalFactory getObject() throws Exception {
        return animalFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return this.animalFactory == null ? AnimalFactory.class : animalFactory.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }


    public String getFactoryName() {
        return factoryName;
    }

    @Required
    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public int getSize() {
        return size;
    }

    @Required
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("实例化调用");
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println();
    }
}
