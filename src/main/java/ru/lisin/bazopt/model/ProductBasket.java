package ru.lisin.bazopt.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductBasket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
