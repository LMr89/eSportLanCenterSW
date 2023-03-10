package com.g4.dev.esportlancentersw.repository;

import com.g4.dev.esportlancentersw.model.Ordenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdenadorRepository extends JpaRepository<Ordenador, Long> {

    @Query(value =  "SELECT ord.direccionMac FROM Ordenador ord WHERE ord.idOrdenador=?1")
    Optional<String> getDireccionMacFromId(Long id);
    boolean existsByIpOrdenador(String ipOrdenador);
    boolean existsByNomDispositivo(String nomDispositivo);
    boolean existsByNumOrdenador(int num);
}
