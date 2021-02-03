package com.li.spring;

import com.li.spring.bean.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.annotation.Resource;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration("classpath:applicationContext.xml")
@SpringJUnitConfig(locations = { "classpath:applicationContext.xml" })
public class Spring5JUnit5Test {

    @Resource
    private Employee employee;

    @Test
    public void test() {
        System.out.println(employee);
    }
}
