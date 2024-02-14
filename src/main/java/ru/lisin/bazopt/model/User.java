package ru.lisin.bazopt.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "li_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String role;
}
