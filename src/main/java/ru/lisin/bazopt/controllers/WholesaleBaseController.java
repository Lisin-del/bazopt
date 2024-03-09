package ru.lisin.bazopt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WholesaleBaseController {
    @GetMapping("/wholesaleBaseHome")
    public String getWholesaleBaseHomePage(Model model) {
        model.addAttribute("values", List.of("hello1", "hello2", "hello3"));
        return "WholesaleBasePage";
    }
}
