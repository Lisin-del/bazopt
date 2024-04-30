package ru.lisin.bazopt.services;

import ru.lisin.bazopt.model.ProductQuantity;

public interface ProductQuantityService {
    ProductQuantity save(ProductQuantity productQuantity);

    ProductQuantity getByID(int id);

    void deleteByID(int id);
}
