package ru.lisin.bazopt.services;

import ru.lisin.bazopt.model.Product;
import ru.lisin.bazopt.model.ProductBasket;

import java.util.List;

public interface ProductBasketService {
    List<ProductBasket> getBasketProductsByUser();

    void putIntoBasket(int productId);

    void deleteProduct(int id);
}
