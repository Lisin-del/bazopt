package ru.lisin.bazopt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "p_technical_char")
public class ProductTechnicalCharacteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(length = 300)
    private String name;
    @Column(length = 500)
    private String materialType;
    private String color;
    @Column(length = 300)
    private String size;
    @Column(length = 400)
    private String useSphere;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;
}
