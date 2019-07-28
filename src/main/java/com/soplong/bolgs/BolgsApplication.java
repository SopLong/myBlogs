package com.soplong.bolgs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.soplong.bolgs.mapper")
public class BolgsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BolgsApplication.class, args);
    }

}
