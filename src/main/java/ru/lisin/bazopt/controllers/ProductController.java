package ru.lisin.bazopt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lisin.bazopt.model.WholesaleBase;
import ru.lisin.bazopt.services.WholesaleBaseService;

import java.util.*;

@Controller
public class ProductController {
    @Autowired
    private WholesaleBaseService wholesaleBaseService;

    @GetMapping("/allProducts")
    public String getAllProducts(Model model) {
        // todo: this code is only for tests
        List<WholesaleBase> bases = wholesaleBaseService.getAllWholesaleBases();
        model.addAttribute("bases", bases);
        return "Products";
    }
}
