package com.g4.dev.esportlancentersw.repository;

import com.g4.dev.esportlancentersw.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor , Long> {
}
