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

    @PostMapping("/test")
    public String getTest(@RequestBody Map<String, String> body) {
        int i = 0;
        return "test";
    }
}
