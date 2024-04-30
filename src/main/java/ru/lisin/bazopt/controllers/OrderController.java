package ru.lisin.bazopt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lisin.bazopt.model.Order;
import ru.lisin.bazopt.services.OrderService;

@Controller
@RequestMapping(path = "/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/all")
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "Orders";
    }

    @GetMapping(path = "/getOrderCreationInfo")
    public String getOrderCreationInfo(Model model) {
        Order order = orderService.getOrderCreationInfo();
        model.addAttribute("order", order);
        return "OrderCreationInfo";
    }

    @PostMapping(path = "/create")
    public void createOrder() {
        orderService.createOrder();
    }
}
