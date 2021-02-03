package com.li.spring;

import com.li.spring.bean.Department;
import com.li.spring.bean.Employee;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.lang.Nullable;

public class Spring5Test {

    @Nullable
    private String name;

    private static final Logger log = LoggerFactory.getLogger(Spring5Test.class);

    private static ApplicationContext ac = null;

    static {
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void testGenericApplicationContext1() {
        // 创建 GenericApplicationContext 对象
        GenericApplicationContext context = new GenericApplicationContext();
        // 调用 context 的方法对象注册
        context.refresh();
        context.registerBean("department", Department.class, () -> new Department(1, "Java"));
        Department department = (Department) context.getBean("department");
        log.info(department.toString());
    }

    @Test
    public void testGenericApplicationContext() {
        // 创建 GenericApplicationContext 对象
        GenericApplicationContext context = new GenericApplicationContext();
        // 调用 context 的方法对象注册
        context.refresh();
        context.registerBean(Department.class, () -> new Department(1, "Java"));
        Department department = context.getBean(Department.class);
        log.info(department.toString());
    }

    @Test
    public void test() {
        Employee employee = ac.getBean("employee", Employee.class);
        System.out.println(employee);
        log.info(employee.toString());
    }
}

