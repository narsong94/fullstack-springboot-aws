package com.springboot.aws.web;

import org.springframework.web.bind.annotation.RestController;

import com.springboot.aws.web.dto.HelloResponseDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HelloController {
    
    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
    

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    
}
