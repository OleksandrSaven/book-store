package com.app.onlinebookstore.repository;

import com.app.onlinebookstore.model.Role;
import com.app.onlinebookstore.model.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName name);
}
