package ru.lisin.bazopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lisin.bazopt.model.ProductQuantity;

@Repository
public interface ProductQuantityRepository extends JpaRepository<ProductQuantity, Integer> {

    @Query(value = "SELECT p FROM ProductQuantity p WHERE p.order.id = :orderID AND p.product.id = :productID")
    ProductQuantity getProductQuantityByOrderIDAndProductID(
            @Param(value = "orderID") int orderID,
            @Param(value = "productID") int productID
    );
}
