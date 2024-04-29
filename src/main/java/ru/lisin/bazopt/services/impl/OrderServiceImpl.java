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

        List<ProductBasket> basketProducts = productBasketService.getBasketProductsByUser();

        long orderPrice = 0;
        for (ProductBasket product : basketProducts) {
            long quantity = product.getQuantity();
            orderPrice += product.getProduct().getPrice() * quantity;
        }

        List<Product> products = basketProducts.stream().map(basketProduct -> basketProduct.getProduct()).toList();

        return Order.builder()
                .user(user)
                .products(products)
                .address(user.getAddress())
                .price(orderPrice)
                .build();
    }
}
