package org.example.dtopattern.User;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "users")
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false,  unique = true, insertable = false, name = "id")
    private Integer id;

    @Column(nullable = false, name = "username")
    private String username;

    @Column(nullable = false, unique = true, name = "password")
    private String password;

    @Column(nullable = false, name = "emial")
    private String email;

}
