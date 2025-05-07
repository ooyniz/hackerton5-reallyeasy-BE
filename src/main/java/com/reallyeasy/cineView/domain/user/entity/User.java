package com.reallyeasy.cineView.domain.user.entity;

import com.reallyeasy.cineView.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

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

    @Builder
    public User(String userName, String password, String name, String bio, Character gender) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.bio = bio;
        this.gender = gender;
    }
}
