package com.g4.dev.esportlancentersw.service.ServicesImpl;

import com.g4.dev.esportlancentersw.model.Reserva;
import com.g4.dev.esportlancentersw.service.IServices.IReservaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservaServiceImpl implements IReservaService {
    @Override
    public List<Reserva> listarDatos() {
        return null;
    }

    @Override
    public Optional<Reserva> buscarEntidad(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<Reserva> listarEntidadPorPagina(Pageable p) {
        return null;
    }

    @Override
    public Reserva registrarEntidad(Reserva entidad) {
        return null;
    }

    @Override
    public Reserva modificarEntidad(Reserva entidad) {
        return null;
    }

    @Override
    public boolean eliminarEntidad(Long id) {
        return false;
    }
}
