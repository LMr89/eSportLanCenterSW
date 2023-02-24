package com.g4.dev.esportlancentersw.repository;

import com.g4.dev.esportlancentersw.model.PedidoCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoCompraRepository extends JpaRepository<PedidoCompra, Long> {
}
