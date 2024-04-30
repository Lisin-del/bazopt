package ru.lisin.bazopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lisin.bazopt.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
