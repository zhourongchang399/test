package org.example.controller;

import org.example.entity.Test;
import org.example.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/test")
    public String test() {
        return "success";
    }
    @PostMapping("/test")
    public String post() {
        return "successpost";
    }

    @GetMapping("/getTestList")
    public List<Test> getList() {
        return testService.getTestList();
    }
}
