package ru.lisin.bazopt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lisin.bazopt.model.User;
import ru.lisin.bazopt.services.UserService;

@Controller
@RequestMapping(path = "/user")
public class UserProfileController {
    private final UserService userService;

    @Autowired
    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/getUserProfile")
    public String getUserProfile(Model model) {
        User currentUser = userService.getCurrentUser();
        model.addAttribute("user", currentUser);
        return "UserProfile";
    }
}
