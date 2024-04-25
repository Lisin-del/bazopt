package ru.lisin.bazopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lisin.bazopt.model.DebitCard;

@Repository
public interface DebitCardRepository extends JpaRepository<DebitCard, Integer> {

    @Query(value = "SELECT c FROM DebitCard c WHERE c.cardNumber = :number")
    DebitCard getDebitCardByNumber(@Param(value = "number") String number);

    @Query(value = "SELECT c FROM DebitCard c WHERE c.user.id = :userID")
    DebitCard getDebitCardByUserID(@Param(value = "userID") long userID);
}
