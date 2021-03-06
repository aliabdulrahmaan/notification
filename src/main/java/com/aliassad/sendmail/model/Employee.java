package com.aliassad.sendmail.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {

    private Long id;
    private String  name;
    private String position;
    private String empId;
}
