package ru.lisin.bazopt.repository;

import ru.lisin.bazopt.model.Product;
import ru.lisin.bazopt.model.ProductFilter;

import java.util.List;

public interface CustomProductRepository {
    List<Product> getProductsWithFilter(ProductFilter filter);
}
