package kr.ac.jejunu;

import kr.ac.jejunu.Hello.Hello;
import kr.ac.jejunu.Hello.HelloImpl;
import kr.ac.jejunu.Hello.HelloPerson;
import kr.ac.jejunu.Hello.HelloPersonImpl;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanNameReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by JKKim on 2016. 4. 29..
 */
public class StaticApplicationContextTest {
    @Test
    public void applicationContext() {
        StaticApplicationContext applicationContext = new StaticApplicationContext();
        applicationContext.registerSingleton("hello", HelloImpl.class);
        Hello hello = applicationContext.getBean("hello", Hello.class);
        assertThat(hello.sayHello(), is("Hello!!"));
    }

    @Test
    public void applicationContextUsingBeanDefinition() {
        StaticApplicationContext applicationContext = new StaticApplicationContext();
        applicationContext.registerSingleton("hello", HelloImpl.class);

        BeanDefinition beanDefinition = new RootBeanDefinition(HelloPersonImpl.class);
        beanDefinition.getPropertyValues().addPropertyValue("name", "김재관");
        beanDefinition.getPropertyValues().addPropertyValue("hello", new RuntimeBeanNameReference("hello"));

        applicationContext.registerBeanDefinition("helloPerson", beanDefinition);
        HelloPerson helloPerson = applicationContext.getBean("helloPerson", HelloPerson.class);
        assertThat(helloPerson.sayHello(), is("hello!! 김재관"));
    }
}
