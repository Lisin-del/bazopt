package ru.lisin.bazopt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lisin.bazopt.model.*;
import ru.lisin.bazopt.repository.OrderRepository;
import ru.lisin.bazopt.service.*;

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

        Order newOrder = Order.builder()
                .id(order.getId())
                .user(order.getUser())
                .address(order.getAddress())
                .price(order.getPrice())
                .products(new ArrayList<>() {{addAll(order.getProducts());}})
                .build();

        List<ProductBasket> basketProducts = productBasketService.getBasketProductsByUser();

        List<ProductQuantity> productQuantities = new ArrayList<>();

        basketProducts.forEach(basketProduct -> {
            int productID = basketProduct.getProduct().getId();
            Product product = productService.getProductById(productID);
            product.setQuantity(product.getQuantity() - basketProduct.getQuantity());
            productService.saveProduct(product);

            ProductQuantity productQuantity = ProductQuantity.builder()
                    .product(product)
                    //.order(newOrder)
                    .quantity(basketProduct.getQuantity())
                    .build();

            productQuantityService.save(productQuantity);

            productQuantities.add(productQuantity);

            productBasketService.deleteProduct(basketProduct.getId());
        });

        newOrder.setProductQuantities(productQuantities);

        return orderRepository.save(newOrder);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrderByID(int id) {
        Order deletedOrder = orderRepository.findById(id).orElse(null);

        deletedOrder.getProducts().forEach(orderProduct -> {
            ProductQuantity productQuantity = productQuantityService.getProductQuantityByOrderIDAndProductID(
                    deletedOrder.getId(),
                    orderProduct.getId()
            );

            orderProduct.setQuantity(productQuantity.getQuantity() + orderProduct.getQuantity());
            productService.saveProduct(orderProduct);
        });

        orderRepository.deleteById(id);
    }
}
