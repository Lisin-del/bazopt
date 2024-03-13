package ru.lisin.bazopt.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "wholesale_base", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@Data
public class WholesaleBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @Column(length = 600)
    private String description;
    private String address;
    private String basePhotoName;
    private String phoneNumber;
    private String bankData;
    // update db
    // update sql scripts to fill the db
}
