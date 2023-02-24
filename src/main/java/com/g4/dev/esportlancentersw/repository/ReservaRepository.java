package com.g4.dev.esportlancentersw.repository;

import com.g4.dev.esportlancentersw.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva ,Long> {
}
