package com.li.webfluxfunction.service;

import com.li.webfluxfunction.entity.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// 用户操作接口
public interface EmployeeService {

    // 根据 id 查询员工
    Mono<Employee> getEmpById(int id);

    // 查询所有用户
    Flux<Employee> getAllEmps();

    // 添加用户
    Mono<Void> saveEmp(Mono<Employee> emp);
}
