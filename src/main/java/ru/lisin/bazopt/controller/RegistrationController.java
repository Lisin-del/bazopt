package ru.lisin.bazopt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lisin.bazopt.model.User;
import ru.lisin.bazopt.service.UserService;

@Controller
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String registerNewUser(@RequestBody User user, Model model) {
        User existingUser = userService.getUserByEmail(user.getEmail());
        if (existingUser != null) {
            model.addAttribute("error", "User with the same email exists bddd!");
            return "error";
        }
        userService.createUser(user);
        return "";
    }

    @GetMapping(path = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserTest(@RequestParam(name = "userId") long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping(path = "/registration")
    public String getRegistration() {
        return "registration";
    }

    @GetMapping(path = "/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping(path = "/registrationError")
    public String getRegistrationErrorPage() {
        return "RegError";
    }
}
