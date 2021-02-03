package com.li.webfluxfunction.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Employee {

    private Integer id;

    private String empName;

    private String phone;
}
