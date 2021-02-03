package com.li.webfluxfunction.service.impl;

import com.li.webfluxfunction.entity.Employee;
import com.li.webfluxfunction.service.EmployeeService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<Integer, Employee> emps = new HashMap<>();

    {
        emps.put(1, new Employee(1, "LiXL", "1234456"));
        emps.put(2, new Employee(2, "LiLX", "12344567"));
        emps.put(3, new Employee(3, "YuCX", "123445678"));
    }

    @Override
    public Mono<Employee> getEmpById(int id) {
        return Mono.justOrEmpty(emps.get(id));
    }

    @Override
    public Flux<Employee> getAllEmps() {
        return Flux.fromIterable(emps.values());
    }

    @Override
    public Mono<Void> saveEmp(Mono<Employee> empMono) {
        return empMono.doOnNext(e -> {
            int id = emps.size() + 1;
            e.setId(id);
            emps.put(id, e);
        }).then(Mono.empty());
    }
}
