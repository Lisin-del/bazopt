package ru.lisin.bazopt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lisin.bazopt.model.WholesaleBase;
import ru.lisin.bazopt.services.WholesaleBaseService;

import java.util.List;

@Controller
public class WholesaleBaseController {
    private final WholesaleBaseService wholesaleBaseService;

    @Autowired
    public WholesaleBaseController(WholesaleBaseService wholesaleBaseService) {
        this.wholesaleBaseService = wholesaleBaseService;
    }

    @GetMapping("/wholesaleBaseHome")
    public String getWholesaleBaseHomePage(Model model) {
        List<WholesaleBase> bases = wholesaleBaseService.getAllWholesaleBases();
        model.addAttribute("bases", bases);
        return "WholesaleBasePage";
    }
}
