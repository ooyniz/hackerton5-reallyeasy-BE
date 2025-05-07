package com.reallyeasy.cineView.domain.user.repository;

import com.reallyeasy.cineView.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(String Id);
    Optional<User> findByUserName(String UserName);
    Optional<Boolean> existsUserName(String userName);
}
