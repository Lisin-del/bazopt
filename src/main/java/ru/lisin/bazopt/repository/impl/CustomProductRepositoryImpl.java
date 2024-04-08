package ru.lisin.bazopt.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.lisin.bazopt.model.Product;
import ru.lisin.bazopt.model.ProductFilter;
import ru.lisin.bazopt.model.ProductTechnicalCharacteristic;
import ru.lisin.bazopt.model.WholesaleBase;
import ru.lisin.bazopt.repository.CustomProductRepository;
import ru.lisin.bazopt.repository.ProductTechnicalCharRepository;
import ru.lisin.bazopt.repository.WholesaleBaseRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class CustomProductRepositoryImpl implements CustomProductRepository {
    private final DataSource dataSource;
    private final WholesaleBaseRepository baseRepository;
    private final ProductTechnicalCharRepository productTechnicalCharRepository;

    @Autowired
    public CustomProductRepositoryImpl(
            DataSource dataSource,
            WholesaleBaseRepository baseRepository,
            ProductTechnicalCharRepository productTechnicalCharRepository
    ) {
        this.dataSource = dataSource;
        this.baseRepository = baseRepository;
        this.productTechnicalCharRepository = productTechnicalCharRepository;
    }

    @Override
    public List<Product> getProductsWithFilter(ProductFilter filter) {
        final String emptyStringValue = "---";
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM product");

        if (filter.getBase() != null && !filter.getBase().isEmpty() && !filter.getBase().equalsIgnoreCase(emptyStringValue)) {
            queryBuilder.append(" WHERE");
            WholesaleBase base = baseRepository.getWholesaleBaseByName(filter.getBase());
            queryBuilder.append(" base_id = ").append(base.getId());
        }

        if (filter.getProducer() != null && !filter.getProducer().isEmpty() && !filter.getProducer().equalsIgnoreCase(emptyStringValue)) {
            addWhereOrAndClauses(queryBuilder);
            queryBuilder.append(" LOWER(producer) = ").append("'").append(filter.getProducer().toLowerCase()).append("'");
        }

        if (filter.getPrice() != 0) {
            addWhereOrAndClauses(queryBuilder);
            queryBuilder.append(" price <= ").append(filter.getPrice());
        }

        if (filter.getQuantity() != 0) {
            addWhereOrAndClauses(queryBuilder);
            queryBuilder.append(" quantity >= ").append(filter.getQuantity());
        }

        List<Product> filteredProducts = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int baseId = resultSet.getInt("base_id");
                Optional<WholesaleBase> base = baseRepository.findById(baseId);
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getFloat("price"),
                        resultSet.getString("photo_name"),
                        resultSet.getString("producer"),
                        base.orElse(null),
                        resultSet.getLong("quantity")
                );
                filteredProducts.add(product);
            }
        } catch (SQLException e) {
            log.info("Product filtering failed", e);
            return null;
        }
        return filteredProducts;
    }

    private void addWhereOrAndClauses(StringBuilder queryBuilder) {
        int whereIndex = queryBuilder.indexOf("WHERE");
        if (whereIndex != -1) {
            queryBuilder.append(" AND");
        } else {
            queryBuilder.append(" WHERE");
        }
    }
}
