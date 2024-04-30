package ru.lisin.bazopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lisin.bazopt.model.ProductQuantity;

@Repository
public interface ProductQuantityRepository extends JpaRepository<ProductQuantity, Integer> {
}
