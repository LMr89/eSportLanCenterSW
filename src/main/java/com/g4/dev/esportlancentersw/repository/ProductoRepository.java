package com.g4.dev.esportlancentersw.repository;

import com.g4.dev.esportlancentersw.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombre(String nombre);
    @Query(value = "SELECT p.stock FROM Producto p WHERE p.idProducto  = ?1" )
    int getStockByIdProducto(long idProducto);
}
