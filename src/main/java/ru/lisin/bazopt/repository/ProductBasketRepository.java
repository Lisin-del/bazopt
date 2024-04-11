package ru.lisin.bazopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lisin.bazopt.model.ProductBasket;

import java.util.List;

@Repository
public interface ProductBasketRepository extends JpaRepository<ProductBasket, Long> {
    @Query(value = "SELECT bp FROM ProductBasket bp WHERE bp.user.id = :userId")
    List<ProductBasket> getBasketProductByUser(@Param(value = "userId") long userId);
}
