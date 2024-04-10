package ru.lisin.bazopt.services;

import ru.lisin.bazopt.model.ProductTechnicalCharacteristic;

public interface ProductTechnicalCharacteristicService {
    ProductTechnicalCharacteristic getByProductId(int id);
}
