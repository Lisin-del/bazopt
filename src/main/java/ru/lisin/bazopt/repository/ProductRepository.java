package ru.lisin.bazopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lisin.bazopt.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT p FROM Product p WHERE LOWER(p.name) LIKE %:userSearchText%")
    List<Product> getProductsByUserSearchText(@Param(value = "userSearchText") String userSearchText);

    @Query(value = "SELECT p FROM Product p WHERE p.base.id = :baseID")
    List<Product> getProductsByBaseID(@Param(value = "baseID") int baseID);

    @Query(value = "SELECT * FROM product p WHERE LOWER(p.name) LIKE %:productName% ORDER BY p.price ASC FETCH FIRST 1 ROWS ONLY", nativeQuery = true)
    Product getProductWithBestPriceByName(@Param(value = "productName") String productName);
}
