package ru.lisin.bazopt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lisin.bazopt.model.Order;
import ru.lisin.bazopt.services.OrderService;
import ru.lisin.bazopt.services.ProductQuantityService;

import java.util.List;

@Controller
@RequestMapping(path = "/order")
public class OrderController {
    private final OrderService orderService;
    private final ProductQuantityService productQuantityService;

    @Autowired
    public OrderController(OrderService orderService, ProductQuantityService productQuantityService) {
        this.orderService = orderService;
        this.productQuantityService = productQuantityService;
    }

    @GetMapping(path = "/all")
    public String getAllOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);

        model.addAttribute("quantity", productQuantityService.getProductQuantityByOrders(orders));

        return "Orders";
    }

    @GetMapping(path = "/getOrderCreationInfo")
    public String getOrderCreationInfo(Model model) {
        Order order = orderService.getOrderCreationInfo();
        model.addAttribute("order", order);
        return "OrderCreationInfo";
    }

    @PostMapping(path = "/create")
    @ResponseBody
    public void createOrder() {
        orderService.createOrder();
    }

    @DeleteMapping(path = "/delete")
    @ResponseBody
    public void deleteOrderByID(@RequestParam(name = "id") int id) {
        orderService.deleteOrderByID(id);
    }
}
