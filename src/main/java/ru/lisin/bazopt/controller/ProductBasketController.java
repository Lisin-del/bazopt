package ru.lisin.bazopt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lisin.bazopt.model.ProductBasket;
import ru.lisin.bazopt.service.ProductBasketService;

import java.util.List;

@Controller
public class ProductBasketController {
    private final ProductBasketService productBasketService;

    @Autowired
    public ProductBasketController(ProductBasketService productBasketService) {
        this.productBasketService = productBasketService;
    }

    @PostMapping(path = "/basket/put")
    public String putIntoBasket(
            @RequestParam(name = "productId") int productId,
            @RequestParam(name = "quantity") long quantity
    ) {
        productBasketService.putIntoBasket(productId, quantity);
        return "redirect:/basket";
    }

    @GetMapping(path = "/basket")
    public String getBasketPage(Model model) {
        List<ProductBasket> basketProductsByUser = productBasketService.getBasketProductsByUser();
        model.addAttribute("basketProducts", basketProductsByUser);
        return "ProductBasket";
    }

    @DeleteMapping(path = "/basket/delete")
    @ResponseBody
    public void deleteProduct(@RequestParam(name = "id") int id) {
        productBasketService.deleteProduct(id);
    }
}
