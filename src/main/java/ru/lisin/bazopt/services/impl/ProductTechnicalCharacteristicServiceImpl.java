package ru.lisin.bazopt.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lisin.bazopt.model.ProductTechnicalCharacteristic;
import ru.lisin.bazopt.repository.ProductTechnicalCharRepository;
import ru.lisin.bazopt.services.ProductTechnicalCharacteristicService;

@Service
public class ProductTechnicalCharacteristicServiceImpl implements ProductTechnicalCharacteristicService {
    private final ProductTechnicalCharRepository repository;

    @Autowired
    public ProductTechnicalCharacteristicServiceImpl(ProductTechnicalCharRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductTechnicalCharacteristic getByProductId(int id) {
        return repository.getByProductId(id);
    }
}
