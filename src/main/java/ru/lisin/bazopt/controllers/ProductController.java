package ru.lisin.bazopt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/allProducts/filter")
    public String getAllProductsWithFilter(@RequestBody ProductFilter filter, Model model) {
        List<Product> products = productService.getAllProductsWithFilter(filter);
        model.addAttribute("products", products);
        return "Products";
    }
}
