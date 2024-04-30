package ru.lisin.bazopt.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lisin.bazopt.model.ProductQuantity;
import ru.lisin.bazopt.repository.ProductQuantityRepository;
import ru.lisin.bazopt.services.ProductQuantityService;

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
}
