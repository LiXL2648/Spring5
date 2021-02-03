package com.li.webfluxfunction;

import com.li.webfluxfunction.entity.Employee;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

public class Client {

    public static void main(String[] args) {

        // 调用服务器地址
        WebClient webClient = WebClient.create("http://localhost:8082");

        // 根据Id查询
        String id = "1";
        Employee employee = webClient.get().uri("/employee/{id}", id).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(Employee.class).block();
        System.out.println(employee);

        Flux<Employee> employeeFlux = webClient.get().uri("/employee").accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToFlux(Employee.class);
        employeeFlux.map(Employee::getEmpName).buffer().doOnNext(System.out::println).blockFirst();
    }
}
