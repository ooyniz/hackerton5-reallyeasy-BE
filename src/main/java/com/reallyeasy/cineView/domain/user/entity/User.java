package com.reallyeasy.cineView.domain.user.entity;

import com.reallyeasy.cineView.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity implements UserDetails {

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

    private String role;

    @Builder
    public User(String userName, String password, String name, String bio, Character gender, String role) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.bio = bio;
        this.gender = gender;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
