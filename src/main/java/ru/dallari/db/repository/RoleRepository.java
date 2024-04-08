package ru.dallari.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dallari.db.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
