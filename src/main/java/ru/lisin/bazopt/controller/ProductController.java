package ru.lisin.bazopt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lisin.bazopt.model.Product;
import ru.lisin.bazopt.model.ProductFilter;
import ru.lisin.bazopt.model.ProductTechnicalCharacteristic;
import ru.lisin.bazopt.service.ProductService;
import ru.lisin.bazopt.service.ProductTechnicalCharacteristicService;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;
    private final ProductTechnicalCharacteristicService productTechnicalCharacteristicService;

    @Autowired
    public ProductController(
            ProductService productService,
            ProductTechnicalCharacteristicService productTechnicalCharacteristicService
    ) {
        this.productService = productService;
        this.productTechnicalCharacteristicService = productTechnicalCharacteristicService;
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

    @GetMapping(path = "/product/{id}")
    public String getProductById(@PathVariable(name = "id") int id, Model model) {
        Product product = productService.getProductById(id);
        ProductTechnicalCharacteristic productCharacteristic = productTechnicalCharacteristicService.getByProductId(
                product.getId()
        );

        model.addAttribute("product", product);
        model.addAttribute("productChar", productCharacteristic);

        return "Product";
    }

    @GetMapping(path = "/products/search")
    public String getProductsByUserSearchText(@RequestParam(name = "userSearch") String userSearchText, Model model) {
        List<Product> productsByUserSearchText = productService.getProductsByUserSearchText(userSearchText);
        model.addAttribute("products", productsByUserSearchText);
        return "Products";
    }

    @GetMapping(path = "/products/bases")
    public String getProductsByBaseID(@RequestParam(name = "baseID") int baseID, Model model) {
        List<Product> productsByBaseID = productService.getProductsByBaseID(baseID);
        model.addAttribute("products", productsByBaseID);
        return "Products";
    }

    @GetMapping(path = "/product/bestPrice")
    public String getProductWithBestPrice(@RequestParam(name = "productID") int productID, Model model) {
        Product product = productService.getProductWithBestPrice(productID);
        ProductTechnicalCharacteristic productCharacteristic = productTechnicalCharacteristicService.getByProductId(
                product.getId()
        );
        model.addAttribute("product", product);
        model.addAttribute("productChar", productCharacteristic);
        return "Product";
    }
}
