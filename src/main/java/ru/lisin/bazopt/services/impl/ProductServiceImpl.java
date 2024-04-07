package ru.lisin.bazopt.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lisin.bazopt.model.Product;
import ru.lisin.bazopt.model.ProductFilter;
import ru.lisin.bazopt.model.WholesaleBase;
import ru.lisin.bazopt.repository.ProductRepository;
import ru.lisin.bazopt.repository.WholesaleBaseRepository;
import ru.lisin.bazopt.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final WholesaleBaseRepository baseRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, WholesaleBaseRepository baseRepository) {
        this.productRepository = productRepository;
        this.baseRepository = baseRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsWithFilter(ProductFilter filter) {
        StringBuilder queryBuilder = new StringBuilder();
        if (filter.getBase() != null && !filter.getBase().isEmpty()) {
            WholesaleBase base = baseRepository.getWholesaleBaseByName(filter.getBase());
            queryBuilder.append(" p.base_id = ").append(base.getId());
        }
        if (filter.getProducer() != null && !filter.getProducer().isEmpty()) {
            int index = queryBuilder.lastIndexOf("AND");
            if (index == -1) {
                queryBuilder.append(" AND");
            }
            queryBuilder.append(" LOWER(p.producer) = ").append("'").append(filter.getProducer().toLowerCase()).append("'");
        }
        productRepository.getProductsWithFilter(queryBuilder.toString());
        return null;
    }
}
