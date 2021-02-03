package com.li.spring;

import com.li.spring.bean.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Spring5JUnit4Test {

    @Autowired
    private Employee employee;

    @Test
    public void test() {
        System.out.println(employee);
    }
}
