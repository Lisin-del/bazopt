package ru.lisin.bazopt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.lisin.bazopt.model.Product;
import ru.lisin.bazopt.services.ProductBasketService;

import java.util.List;

@Controller
public class ProductBasketController {
    private final ProductBasketService productBasketService;

    @Autowired
    public ProductBasketController(ProductBasketService productBasketService) {
        this.productBasketService = productBasketService;
    }

    @PostMapping(path = "/basket/add")
    public void putIntoBasket(@RequestParam(name = "productId") int productId) {

    }

    @GetMapping(path = "/basket")
    public String getBasketPage(Model model) {
        List<Product> basketProductsByUser = productBasketService.getBasketProductsByUser();
        model.addAttribute("products", basketProductsByUser);
        return "ProductBasket";
    }
}
