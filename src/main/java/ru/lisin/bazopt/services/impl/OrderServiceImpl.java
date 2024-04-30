package ru.lisin.bazopt.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lisin.bazopt.model.*;
import ru.lisin.bazopt.repository.OrderRepository;
import ru.lisin.bazopt.services.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final ProductBasketService productBasketService;
    private final UserService userService;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final ProductQuantityService productQuantityService;

    @Autowired
    public OrderServiceImpl(
            ProductBasketService productBasketService,
            UserService userService,
            ProductService productService,
            OrderRepository orderRepository,
            ProductQuantityService productQuantityService
    ) {
        this.productBasketService = productBasketService;
        this.userService = userService;
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.productQuantityService = productQuantityService;
    }

    @Override
    public Order getOrderCreationInfo() {
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

    @Override
    public Order createOrder() {
        Order order = getOrderCreationInfo();

        List<ProductBasket> basketProducts = productBasketService.getBasketProductsByUser();

        List<ProductQuantity> productQuantities = new ArrayList<>();

        basketProducts.forEach(basketProduct -> {
            int productID = basketProduct.getProduct().getId();
            Product product = productService.getProductById(productID);
            product.setQuantity(product.getQuantity() - basketProduct.getQuantity());
            productService.saveProduct(product);

            ProductQuantity productQuantity = ProductQuantity.builder()
                    .product(product)
                    .quantity(basketProduct.getQuantity())
                    .build();
            productQuantities.add(productQuantity);
        });

        order.setProductQuantities(productQuantities);

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
