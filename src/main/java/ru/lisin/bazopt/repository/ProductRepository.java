package ru.lisin.bazopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lisin.bazopt.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
