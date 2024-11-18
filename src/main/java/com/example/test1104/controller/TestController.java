package com.example.test1104.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class TestController {

    @GetMapping("/thymeleaf")
    public String test111(){
        return "thymeleaf";
    }

}
