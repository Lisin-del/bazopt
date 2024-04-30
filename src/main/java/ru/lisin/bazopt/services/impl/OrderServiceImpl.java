package ru.lisin.bazopt.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lisin.bazopt.model.Order;
import ru.lisin.bazopt.model.Product;
import ru.lisin.bazopt.model.ProductBasket;
import ru.lisin.bazopt.model.User;
import ru.lisin.bazopt.repository.OrderRepository;
import ru.lisin.bazopt.services.OrderService;
import ru.lisin.bazopt.services.ProductBasketService;
import ru.lisin.bazopt.services.ProductService;
import ru.lisin.bazopt.services.UserService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final ProductBasketService productBasketService;
    private final UserService userService;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(
            ProductBasketService productBasketService,
            UserService userService,
            ProductService productService,
            OrderRepository orderRepository
    ) {
        this.productBasketService = productBasketService;
        this.userService = userService;
        this.productService = productService;
        this.orderRepository = orderRepository;
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

        return Order.builder()
                .user(user)
                .basketProducts(basketProducts)
                .address(user.getAddress())
                .price(orderPrice)
                .build();
    }

    @Override
    public Order createOrder() {
        Order order = getOrderCreationInfo();

        List<ProductBasket> basketProducts = order.getBasketProducts();

        basketProducts.forEach(basketProduct -> {
            int productID = basketProduct.getProduct().getId();
            Product product = productService.getProductById(productID);
            product.setQuantity(product.getQuantity() - basketProduct.getQuantity());
            productService.saveProduct(product);
        });

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
