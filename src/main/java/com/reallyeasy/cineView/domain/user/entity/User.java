package com.reallyeasy.cineView.domain.user.entity;

import com.reallyeasy.cineView.domain.BaseTimeEntity;
import com.reallyeasy.cineView.domain.favoriteMovie.entity.FavoriteMovie;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String userName;

    private String password;

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String bio;

    private Character gender;

    private String role;

    @OneToMany(mappedBy = "user")
    private List<FavoriteMovie> favorites;

    @Builder
    public User(String userName, String password, String name, String bio, Character gender, String role) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.bio = bio;
        this.gender = gender;
        this.role = role;
    }

    public void updateUser(String userName, String password, String name, String bio, Character gender) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.bio = bio;
        this.gender = gender;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
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
