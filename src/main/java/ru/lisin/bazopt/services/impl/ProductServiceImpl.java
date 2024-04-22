package ru.lisin.bazopt.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lisin.bazopt.model.Product;
import ru.lisin.bazopt.model.ProductFilter;
import ru.lisin.bazopt.model.WholesaleBase;
import ru.lisin.bazopt.repository.CustomProductRepository;
import ru.lisin.bazopt.repository.ProductRepository;
import ru.lisin.bazopt.repository.WholesaleBaseRepository;
import ru.lisin.bazopt.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final WholesaleBaseRepository baseRepository;
    private final CustomProductRepository customProductRepository;

    @Autowired
    public ProductServiceImpl(
            ProductRepository productRepository,
            WholesaleBaseRepository baseRepository,
            CustomProductRepository customProductRepository
    ) {
        this.productRepository = productRepository;
        this.baseRepository = baseRepository;
        this.customProductRepository = customProductRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsWithFilter(ProductFilter filter) {
        return customProductRepository.getProductsWithFilter(filter);
    }

    @Override
    public Product getProductById(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElse(null);
    }

    @Override
    public List<Product> getProductsByUserSearchText(String userSearchText) {
        return productRepository.getProductsByUserSearchText(userSearchText);
    }
}
