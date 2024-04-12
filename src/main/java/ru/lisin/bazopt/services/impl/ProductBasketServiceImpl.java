package ru.lisin.bazopt.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
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
    public List<ProductBasket> getBasketProductsByUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(userEmail);
        return productBasketRepository.getBasketProductByUser(user.getId());
    }

    @Override
    public void putIntoBasket(int productId) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(userEmail);
        ProductBasket productBasket = ProductBasket.builder()
                .user(user)
                .product(productService.getProductById(productId))
                .build();
        productBasketRepository.save(productBasket);
    }

    @Override
    public void deleteProduct(int id) {
        productBasketRepository.deleteById(Long.valueOf(id));
    }
}
