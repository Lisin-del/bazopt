package ru.lisin.bazopt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Entity(name = "u_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @ManyToMany
    private List<Product> products;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(length = 500)
    private String address;
    private long price;
}
