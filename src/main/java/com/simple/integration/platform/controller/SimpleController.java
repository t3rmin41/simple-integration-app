package com.simple.integration.platform.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @RequestMapping(value = "/rs/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello from REST";
    }
}
