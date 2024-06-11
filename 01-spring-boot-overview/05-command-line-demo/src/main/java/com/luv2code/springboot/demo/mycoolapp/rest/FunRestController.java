package com.luv2code.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    @Value("${person.name}")
    private String name;
    @Value("${person.name2}")
    private String name2;

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

    @GetMapping("/name2")
    public String sayHelloName2(){
        return "Hello " + this.name2 + " !";
    }

    @GetMapping("/workout")
    public String getDailyWorkout(){
        return "Run a hard 5k";
    }

    @GetMapping("/fortune")
    public String getDailyFortune(){
        return "Today is your lucky day";
    }
}
