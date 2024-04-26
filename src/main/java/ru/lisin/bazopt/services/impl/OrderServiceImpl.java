package ru.lisin.bazopt.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lisin.bazopt.model.Order;
import ru.lisin.bazopt.model.ProductBasket;
import ru.lisin.bazopt.services.OrderService;
import ru.lisin.bazopt.services.ProductBasketService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final ProductBasketService productBasketService;

    @Autowired
    public OrderServiceImpl(ProductBasketService productBasketService) {
        this.productBasketService = productBasketService;
    }

    @Override
    public Order createOrder() {
        List<ProductBasket> basketProducts = productBasketService.getBasketProductsByUser();

        return null;
    }
}
