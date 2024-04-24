package ru.lisin.bazopt.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "cardNumber", columnNames = {"card_number"})})
@Data
public class DebitCard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String userFullName;
    private String cardNumber;
    private String expirationDate;
    private int cvv;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
