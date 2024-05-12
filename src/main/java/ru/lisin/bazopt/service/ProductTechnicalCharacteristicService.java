package ru.lisin.bazopt.service;

import ru.lisin.bazopt.model.ProductTechnicalCharacteristic;

public interface ProductTechnicalCharacteristicService {
    ProductTechnicalCharacteristic getByProductId(int id);
}
