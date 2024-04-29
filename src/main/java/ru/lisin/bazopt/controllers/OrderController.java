package ru.lisin.bazopt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lisin.bazopt.model.Order;
import ru.lisin.bazopt.services.OrderService;

@Controller
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/order/all")
    public String getAllOrders(Model model) {
        return "Orders";
    }

    @GetMapping(path = "/order/getOrderCreationInfo")
    public String getOrderCreationInfo(Model model) {
        Order order = orderService.createOrderCreationInfo();
        model.addAttribute("order", order);
        return "OrderCreationInfo";
    }
}
