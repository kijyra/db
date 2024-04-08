package ru.dallari.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dallari.db.entity.AuthUser;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByUsername(String username);
}