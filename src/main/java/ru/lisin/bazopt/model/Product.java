package ru.lisin.bazopt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Map;

@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    @Column(length = 1000)
    private String description;
    private float price;
    private String photoName;
    private String producer; //россия, германия, италия, швеция
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "base_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private WholesaleBase base;
    private long quantity;
}

