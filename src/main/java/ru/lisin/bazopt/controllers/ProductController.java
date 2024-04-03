package ru.lisin.bazopt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lisin.bazopt.model.Product;
import ru.lisin.bazopt.model.ProductTechnicalCharacteristic;
import ru.lisin.bazopt.model.WholesaleBase;
import ru.lisin.bazopt.repository.ProductRepository;
import ru.lisin.bazopt.services.WholesaleBaseService;

import java.util.*;

@Controller
public class ProductController {
    @Autowired
    private WholesaleBaseService wholesaleBaseService;

    @Autowired
    private ProductRepository repository;

    @GetMapping("/allProducts")
    public String getAllProducts(Model model) {
        // todo: this code is only for tests
        List<WholesaleBase> bases = wholesaleBaseService.getAllWholesaleBases();
        Product product = new Product();
        product.setBase(bases.get(0));
        product.setName("Штукатурка");
        product.setDescription("This is a test description");
        product.setPrice(600.4f);
        product.setProducer("Russia");
        product.setPhotoName("CoolPhoto");
        ProductTechnicalCharacteristic productTechnicalCharacteristic = new ProductTechnicalCharacteristic() {{
            setName("plaster");
            setColor("white");
            setSize("36kg");
            setUseSphere("plaster sphere");
            setMaterialType("plaster type");
            setProduct(product);
        }};
        product.setCharacteristic(productTechnicalCharacteristic);

        Product save = repository.save(product);

        model.addAttribute("bases", bases);
        return "Products";
    }
}
