package com.li.webfluxfunction.handler;

import com.li.webfluxfunction.entity.Employee;
import com.li.webfluxfunction.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RestController
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @GetMapping("/employee/{id}")
    public Mono<Employee> employee(@PathVariable("id") int id) {
        return employeeService.getEmpById(id);
    }

    @GetMapping("/employee")
    public Flux<Employee> employee() {
        return employeeService.getAllEmps();
    }

    @PostMapping("/employee")
    public Mono<Void> employee(@RequestBody Employee emp) {
        Mono<Employee> empMono = Mono.just(emp);
        return employeeService.saveEmp(empMono);
    }
}
