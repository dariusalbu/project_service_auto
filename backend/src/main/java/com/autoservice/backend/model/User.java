package com.autoservice.backend.model;

import com.autoservice.backend.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private Role role;
}
