package ru.lisin.bazopt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lisin.bazopt.model.Product;
import ru.lisin.bazopt.model.ProductBasket;
import ru.lisin.bazopt.services.ProductBasketService;

import java.util.List;

@Controller
public class ProductBasketController {
    private final ProductBasketService productBasketService;

    @Autowired
    public ProductBasketController(ProductBasketService productBasketService) {
        this.productBasketService = productBasketService;
    }

    @PostMapping(path = "/basket/put")
    public String putIntoBasket(@RequestParam(name = "productId") int productId) {
        productBasketService.putIntoBasket(productId);
        return "redirect:/basket";
    }

    @GetMapping(path = "/basket")
    public String getBasketPage(Model model) {
        List<ProductBasket> basketProductsByUser = productBasketService.getBasketProductsByUser();
        model.addAttribute("basketProducts", basketProductsByUser);
        return "ProductBasket";
    }

    @RequestMapping(path = "/basket/delete", method = RequestMethod.DELETE)
    public String deleteProduct(@RequestParam(name = "id") int id) {
        productBasketService.deleteProduct(id);
        return "redirect:/basket";
    }
}
