package ru.lisin.bazopt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LoginController {
//    @RequestMapping
//    public String getLoginPage() {
//        return "redirect:/login.html";
//    }

    @GetMapping("/test")
    public String getTest() {
        int i = 0;
        return "test";
    }
}
