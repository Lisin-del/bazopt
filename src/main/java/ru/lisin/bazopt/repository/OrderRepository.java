package ru.lisin.bazopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lisin.bazopt.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "select o from u_order o where o.user.id = :userId")
    List<Order> getOrdersByUser(@Param(value = "userId") long userId);
}
