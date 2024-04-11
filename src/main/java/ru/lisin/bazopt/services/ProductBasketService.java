package ru.lisin.bazopt.services;

import ru.lisin.bazopt.model.Product;

import java.util.List;

public interface ProductBasketService {
    List<Product> getBasketProductsByUser();

    void addPutIntoBasket(long productId);
}
