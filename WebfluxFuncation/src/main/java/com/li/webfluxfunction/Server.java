package com.li.webfluxfunction;

import com.li.webfluxfunction.handler.EmployeeHandler;
import com.li.webfluxfunction.service.EmployeeService;
import com.li.webfluxfunction.service.impl.EmployeeServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

public class Server {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.createReactorServer();
        System.out.println("enter to exit");
        System.in.read();
    }

    // 创建 Router 路由
    public RouterFunction<ServerResponse> routingFunction() {

        // 创建 handler 对象
        EmployeeService employeeService = new EmployeeServiceImpl();
        EmployeeHandler employeeHandler = new EmployeeHandler(employeeService);

        // 设置路由
        return RouterFunctions.route(
                        GET("/employee/{id}").and(accept(MediaType.APPLICATION_JSON)), employeeHandler::getEmpById)
                .andRoute(
                        GET("/employee").and(accept(MediaType.APPLICATION_JSON)), employeeHandler::getAllEmps)
                .andRoute(
                        POST("employee"), employeeHandler::saveEmp);
    }

    // 创建服务器完成适配
    public void createReactorServer() {

        // 路由和handler适配
        RouterFunction<ServerResponse> router = routingFunction();
        HttpHandler httpHandler = toHttpHandler(router);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);

        // 创建服务器
        HttpServer.create().handle(adapter).port(8082).bindNow();
    }
}
