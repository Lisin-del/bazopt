package ru.lisin.bazopt.services;

import ru.lisin.bazopt.model.Order;
import ru.lisin.bazopt.model.ProductQuantity;

import java.util.List;
import java.util.Map;

public interface ProductQuantityService {
    ProductQuantity save(ProductQuantity productQuantity);

    ProductQuantity getByID(int id);

    void deleteByID(int id);

    Map<Integer, Map<Integer, Long>> getProductQuantityByOrders(List<Order> orders);

    ProductQuantity getProductQuantityByOrderIDAndProductID(int orderID, int productID);
}
