package com.stefano.briky.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping(value = "/prova")
    public String hello() {
        return "Hello";
    }
}
