package com.springprime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Connecting to docker image
@SpringBootApplication
// We can search for the configuration if not specified by using @ComponentScan
// And then add the @EnableAutoConfiguration
@RestController
public class Main {
    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
    }
    @GetMapping("/greet")
    public GreetResponse greet () {
        GreetResponse response = new GreetResponse(
                "Hello",
                List.of("java","Next","Springboot"),
                new Person(
                        "Manzi Cedrick",
                        12,
                        3012.21
                )
        ) ;
        return response;
    }
    record Person(
            String name,
            int age,
            double savings
    ){}
    record GreetResponse (
            String greet,
            List<String> FavsLanguages,
            Person person
    ){}
}
