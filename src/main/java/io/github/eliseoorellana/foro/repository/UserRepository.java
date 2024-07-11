package io.github.eliseoorellana.foro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import io.github.eliseoorellana.foro.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String username);
}
