package ru.lisin.bazopt.controllers;

import org.springframework.boot.Banner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.lisin.bazopt.model.User;

import java.util.Map;

@RestController
public class RegistrationController {
    @PostMapping(path = "/register")
    public void registerNewUser(@RequestBody User user) {
        int i = 0;
    }
}
