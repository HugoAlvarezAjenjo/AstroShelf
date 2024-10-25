package com.hugoalvarezajenjo.astroshelf.model;

import com.hugoalvarezajenjo.astroshelf.types.Role;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "acounts")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

}
