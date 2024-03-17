package ru.lisin.bazopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lisin.bazopt.model.WholesaleBase;

import java.util.List;

@Repository
public interface WholesaleBaseRepository extends JpaRepository<WholesaleBase, Integer> {
    @Query(value = "SELECT b FROM WholesaleBase b WHERE b.name = :name")
    WholesaleBase getWholesaleBaseByName(@Param("name") String name);

    @Query(value = "SELECT b FROM WholesaleBase b WHERE LOWER(b.name) LIKE %:userSearch%")
    List<WholesaleBase> getAllNamesByUserSearch(@Param("userSearch") String userSearch);
}
