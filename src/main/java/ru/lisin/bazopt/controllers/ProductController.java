package ru.lisin.bazopt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lisin.bazopt.model.Product;
import ru.lisin.bazopt.model.ProductFilter;
import ru.lisin.bazopt.services.ProductService;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/allProducts")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "Products";
    }

    @GetMapping(path = "/allProducts/filter")
    public String getAllProductsWithFilter(
            Model model,
            @RequestParam(name = "country") String country,
            @RequestParam(name = "base") String base,
            @RequestParam(name = "price") float price,
            @RequestParam(name = "quantity") long quantity
    ) {
        List<Product> products = productService.getAllProductsWithFilter(
                new ProductFilter(country, base, price, quantity)
        );
        model.addAttribute("products", products);
        return "Products";
    }
}
