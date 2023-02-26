package com.g4.dev.esportlancentersw.repository;

import com.g4.dev.esportlancentersw.model.Ordenador;
import com.g4.dev.esportlancentersw.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva ,Long> {
    List<Reserva> findByIdOrdenadorAndFechaInicio(Ordenador ord, Calendar cal);

    //Llamar a un store procedure
    @Procedure(name = "listarReservasPorFecha")
    List<Reserva> getReservasByStoreProcedure(
            @Param("idOrdenador") long idOrdenador,
            @Param("fecharIngresada") String fecha);
}
