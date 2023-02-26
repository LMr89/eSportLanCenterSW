package com.g4.dev.esportlancentersw.repository;

import com.g4.dev.esportlancentersw.model.Ordenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenadorRepository extends JpaRepository<Ordenador, Long> {
    boolean existsByIpOrdenador(String ipOrdenador);
    boolean existsByNomDispositivo(String nomDispositivo);
    boolean existsByNumOrdenador(int num);
}
