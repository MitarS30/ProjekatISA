package com.example.final_project.repository;

import com.example.final_project.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String roleName);
    // Dodaj metode ako su potrebne
}
