package com.sgcib.kata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class TennisGameBootApplication {

    public static void main(String[] args){
        SpringApplication.run(TennisGameBootApplication.class, args);
    }
}
