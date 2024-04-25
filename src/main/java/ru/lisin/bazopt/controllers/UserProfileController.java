package ru.lisin.bazopt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.lisin.bazopt.model.DebitCard;
import ru.lisin.bazopt.model.User;
import ru.lisin.bazopt.services.DebitCardService;
import ru.lisin.bazopt.services.UserService;

@Controller
public class UserProfileController {
    private final UserService userService;
    private final DebitCardService debitCardService;

    @Autowired
    public UserProfileController(UserService userService, DebitCardService debitCardService) {
        this.userService = userService;
        this.debitCardService = debitCardService;
    }

    @GetMapping(path = "/user/getUserProfile")
    public String getUserProfile(Model model) {
        User currentUser = userService.getCurrentUser();
        DebitCard debitCard = debitCardService.getDebitCardByUserID(currentUser.getId());

        if (debitCard != null) {
            model.addAttribute("debitCard", debitCard);
        } else {
            model.addAttribute("debitCard", new DebitCard());
        }

        model.addAttribute("user", currentUser);

        return "UserProfile";
    }

    @PostMapping(path = "/user/update")
    @ResponseBody
    public void updateUserInfo(@RequestBody User user) {
        userService.updateUser(user);
    }
}
