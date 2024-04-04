package ru.lisin.bazopt.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Map;

@Data
@Entity
@Table(name = "product")
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
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductTechnicalCharacteristic characteristic;
    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private WholesaleBase base;
    private long quantity;
}

