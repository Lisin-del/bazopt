package ru.lisin.bazopt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.stream.Stream;

@Entity
@Table(
        name = "li_user",
        uniqueConstraints = {@UniqueConstraint(name = "uniqueEmail", columnNames = {"email"})}
)
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
    @Column(length = 500)
    private String address;

    @AllArgsConstructor
    public enum Role {
        USER("user"),
        ADMIN("admin");

        @Getter
        private String value;

        public Role roleOf(String value) {
            return Stream.of(values()).filter(role -> role.value.equalsIgnoreCase(value)).findFirst().orElse(null);
        }
    }
}
