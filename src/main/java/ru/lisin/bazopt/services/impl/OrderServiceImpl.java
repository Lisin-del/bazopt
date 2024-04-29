package ru.lisin.bazopt.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lisin.bazopt.model.Order;
import ru.lisin.bazopt.model.Product;
import ru.lisin.bazopt.model.ProductBasket;
import ru.lisin.bazopt.model.User;
import ru.lisin.bazopt.services.OrderService;
import ru.lisin.bazopt.services.ProductBasketService;
import ru.lisin.bazopt.services.UserService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final ProductBasketService productBasketService;
    private final UserService userService;

    @Autowired
    public OrderServiceImpl(
            ProductBasketService productBasketService,
            UserService userService
    ) {
        this.productBasketService = productBasketService;
        this.userService = userService;
    }

    @Override
    public Order createOrderCreationInfo() {
        User user = userService.getCurrentUser();

        List<Product> basketProducts = productBasketService.getBasketProductsByUser().stream()
                .map(productBasket -> productBasket.getProduct())
                .toList();

        long orderPrice = 0;
        for (Product product : basketProducts) {
            orderPrice += product.getPrice();
        }

        return Order.builder()
                .user(user)
                .products(basketProducts)
                .address(user.getAddress())
                .price(orderPrice)
                .build();
    }
}
