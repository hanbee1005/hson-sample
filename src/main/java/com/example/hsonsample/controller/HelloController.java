package com.example.hsonsample.controller;

import com.example.hsonsample.dto.HelloResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class HelloController {

    @GetMapping
    public HelloResponse hello() {
        return new HelloResponse("안녕하세요!!!");
    }
}
