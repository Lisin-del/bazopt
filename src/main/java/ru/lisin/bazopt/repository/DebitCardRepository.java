package ru.lisin.bazopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lisin.bazopt.model.DebitCard;

@Repository
public interface DebitCardRepository extends JpaRepository<DebitCard, Integer> {
}
