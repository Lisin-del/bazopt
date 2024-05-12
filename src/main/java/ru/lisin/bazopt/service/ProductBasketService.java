package ru.lisin.bazopt.service;

import ru.lisin.bazopt.model.ProductBasket;

import java.util.List;

public interface ProductBasketService {
    List<ProductBasket> getBasketProductsByUser();

    void putIntoBasket(int productId, long quantity);

    void deleteProduct(int id);
}
