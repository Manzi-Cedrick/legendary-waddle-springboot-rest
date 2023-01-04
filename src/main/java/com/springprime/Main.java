package com.springprime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
// We can search for the configuration if not specified by using @ComponentScan
// And then add the @EnableAutoConfiguration
@RestController
public class Main {
    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
    }
    @GetMapping("/")
    public String greet () {
        return "Hello";
    }
}
