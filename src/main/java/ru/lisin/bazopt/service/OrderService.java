package ru.lisin.bazopt.service;

import ru.lisin.bazopt.model.Order;

import java.util.List;

public interface OrderService {
    Order getOrderCreationInfo();

    Order createOrder();

    List<Order> getAllOrders();

    void deleteOrderByID(int id);
}
