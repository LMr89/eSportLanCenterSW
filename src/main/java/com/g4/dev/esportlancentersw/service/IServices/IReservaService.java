package com.g4.dev.esportlancentersw.service.IServices;

import com.g4.dev.esportlancentersw.model.Reserva;
import com.g4.dev.esportlancentersw.service.IMainService;

import java.util.List;

public interface IReservaService extends IMainService<Reserva> {

    List<Reserva> listarReservasPorFechaActual();
    boolean existsById(long id);
}
