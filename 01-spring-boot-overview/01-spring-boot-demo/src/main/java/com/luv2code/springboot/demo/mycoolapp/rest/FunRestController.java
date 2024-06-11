package com.luv2code.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    @Value("${person.name}")
    private String name;

    @GetMapping("/")
    public String sayHello(){
        return "Hello World!";
    }

    @GetMapping("/fabio")
    public String sayHelloFabio(){
        return "Hello Fabio!";
    }

    @GetMapping("/name")
    public String sayHelloName(){
        return "Hello " + this.name + " !";
    }
}
