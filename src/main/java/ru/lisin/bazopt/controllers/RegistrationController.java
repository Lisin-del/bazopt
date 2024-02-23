package ru.lisin.bazopt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.lisin.bazopt.model.User;
import ru.lisin.bazopt.services.UserService;

@RestController
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register")
    public String registerNewUser(@RequestBody User user) {
        userService.createUser(user);
        int i = 0;
        return "hello";
    }

    @GetMapping(path = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserTest(@RequestParam(name = "userId") long userId) {
        return userService.getUserById(userId);
    }
}
