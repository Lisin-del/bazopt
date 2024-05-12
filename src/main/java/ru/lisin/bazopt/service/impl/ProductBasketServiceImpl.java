package ru.lisin.bazopt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.lisin.bazopt.model.ProductBasket;
import ru.lisin.bazopt.model.User;
import ru.lisin.bazopt.repository.ProductBasketRepository;
import ru.lisin.bazopt.service.ProductBasketService;
import ru.lisin.bazopt.service.ProductService;
import ru.lisin.bazopt.service.UserService;

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
    public List<ProductBasket> getBasketProductsByUser() {
        return productBasketRepository.getBasketProductByUser(userService.getCurrentUser().getId());
    }

    @Override
    public void putIntoBasket(int productId, long quantity) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(userEmail);
        ProductBasket productBasket = ProductBasket.builder()
                .user(user)
                .quantity(quantity)
                .product(productService.getProductById(productId))
                .build();
        productBasketRepository.save(productBasket);
    }

    @Override
    public void deleteProduct(int id) {
        productBasketRepository.deleteById(Long.valueOf(id));
    }
}
