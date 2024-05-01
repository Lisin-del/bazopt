package ru.lisin.bazopt.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lisin.bazopt.model.Order;
import ru.lisin.bazopt.model.Product;
import ru.lisin.bazopt.model.ProductQuantity;
import ru.lisin.bazopt.repository.ProductQuantityRepository;
import ru.lisin.bazopt.services.ProductQuantityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductQuantityServiceImpl implements ProductQuantityService {
    private final ProductQuantityRepository productQuantityRepository;

    @Autowired
    public ProductQuantityServiceImpl(ProductQuantityRepository productQuantityRepository) {
        this.productQuantityRepository = productQuantityRepository;
    }

    @Override
    public ProductQuantity save(ProductQuantity productQuantity) {
        return productQuantityRepository.save(productQuantity);
    }

    @Override
    public ProductQuantity getByID(int id) {
        return productQuantityRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteByID(int id) {

    }

    @Override
    public Map<Integer, Map<Integer, Long>> getProductQuantityByOrders(List<Order> orders) {
        Map<Integer, Map<Integer, Long>> quantity = new HashMap();

        for (Order order : orders) {
            Map<Integer, Long> productQuantity = new HashMap<>();

            for (Product product : order.getProducts()) {
                productQuantity.put(
                        product.getId(),
                        getProductQuantityByOrderIDAndProductID(order.getId(), product.getId()).getQuantity()
                );
            }

            quantity.put(order.getId(), productQuantity);
        }

        return quantity;
    }

    @Override
    public ProductQuantity getProductQuantityByOrderIDAndProductID(int orderID, int productID) {
        return productQuantityRepository.getProductQuantityByOrderIDAndProductID(orderID, productID);
    }
}
