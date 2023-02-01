package com.orange.frame.hello.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${server.port}")
    String port;
    @RequestMapping(value = "/hello")
    public String hello(){
        return "Port "+port+": Hello World";
    }
}
