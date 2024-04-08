package ru.lisin.bazopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lisin.bazopt.model.ProductTechnicalCharacteristic;

@Repository
public interface ProductTechnicalCharRepository extends JpaRepository<ProductTechnicalCharacteristic, Integer> {
    @Query(value = "SELECT p FROM ProductTechnicalCharacteristic p WHERE p.product.id = :productId")
    ProductTechnicalCharacteristic getByProductId(@Param(value = "productId") int productId);
}
