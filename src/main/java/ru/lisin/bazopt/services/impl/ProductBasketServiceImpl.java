package ru.lisin.bazopt.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.lisin.bazopt.model.Product;
import ru.lisin.bazopt.model.ProductBasket;
import ru.lisin.bazopt.model.User;
import ru.lisin.bazopt.repository.ProductBasketRepository;
import ru.lisin.bazopt.services.ProductBasketService;
import ru.lisin.bazopt.services.ProductService;
import ru.lisin.bazopt.services.UserService;

import java.util.List;

@Service
public class ProductBasketServiceImpl implements ProductBasketService {
    private final UserService userService;
    private final ProductBasketRepository productBasketRepository;
    private final ProductService productService;

    @Autowired
    public ProductBasketServiceImpl(
            UserService userService,
            ProductBasketRepository productBasketRepository,
            ProductService productService
    ) {
        this.userService = userService;
        this.productBasketRepository = productBasketRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> getBasketProductsByUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(userEmail);
        List<ProductBasket> basketProducts = productBasketRepository.getBasketProductByUser(user.getId());
        return basketProducts.stream().map(bp -> bp.getProduct()).toList();
    }
}
