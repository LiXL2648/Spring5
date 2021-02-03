package com.li.webfluxfunction.handler;

import com.li.webfluxfunction.entity.Employee;
import com.li.webfluxfunction.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

public class EmployeeHandler {

    private final EmployeeService employeeService;

    public EmployeeHandler(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Mono<ServerResponse> getEmpById(ServerRequest request) {
        // 获取Id
        Integer id = Integer.valueOf(request.pathVariable("id"));
        // 得到数据
        Mono<Employee> empMono = employeeService.getEmpById(id);
        // 使用Reactor操作符flatMap，把empMono进行转换
        return empMono.flatMap(e -> {
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(e))
                    .switchIfEmpty(ServerResponse.notFound().build());
        });
    }

    public Mono<ServerResponse> getAllEmps(ServerRequest request) {
        // 得到数据
        Flux<Employee> emps = employeeService.getAllEmps();
        // 使用Reactor操作符flatMap，把empMono进行转换
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(emps, Employee.class);
    }

    public Mono<ServerResponse> saveEmp(ServerRequest request) {
        // 得到对象
        Mono<Employee> empMono = request.bodyToMono(Employee.class);
        return ServerResponse.ok().build(employeeService.saveEmp(empMono));
    }
}

