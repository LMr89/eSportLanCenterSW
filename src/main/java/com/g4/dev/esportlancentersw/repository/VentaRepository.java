package com.g4.dev.esportlancentersw.repository;

import com.g4.dev.esportlancentersw.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
}
