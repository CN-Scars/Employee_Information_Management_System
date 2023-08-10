package com.scars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan   // 开启对Servlet组件支持
@SpringBootApplication
public class EmployeeInformationManagementSystemBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeInformationManagementSystemBackendApplication.class, args);
    }
}
