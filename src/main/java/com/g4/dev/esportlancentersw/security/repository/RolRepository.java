package com.g4.dev.esportlancentersw.security.repository;

import com.g4.dev.esportlancentersw.security.enums.RolNombre;
import com.g4.dev.esportlancentersw.security.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
