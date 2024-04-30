package ru.lisin.bazopt.services;

import ru.lisin.bazopt.model.Product;
import ru.lisin.bazopt.model.ProductFilter;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> getAllProductsWithFilter(ProductFilter filter);

    Product getProductById(int id);

    List<Product> getProductsByUserSearchText(String userSearchText);

    List<Product> getProductsByBaseID(int baseID);

    Product saveProduct(Product product);
}
