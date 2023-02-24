package com.g4.dev.esportlancentersw.repository;

import com.g4.dev.esportlancentersw.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
