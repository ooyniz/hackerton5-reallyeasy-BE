package com.reallyeasy.cineView.domain.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Builder
@Table(name = "users")
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String userName;
    
    private String password;

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String bio;

    private Character gender;
}
