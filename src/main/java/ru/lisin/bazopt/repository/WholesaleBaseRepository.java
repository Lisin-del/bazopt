package ru.lisin.bazopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lisin.bazopt.model.WholesaleBase;

@Repository
public interface WholesaleBaseRepository extends JpaRepository<WholesaleBase, Integer> {
}
