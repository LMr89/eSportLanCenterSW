package com.g4.dev.esportlancentersw.service.ServicesImpl;

import com.g4.dev.esportlancentersw.exception.common.NotFoundException;
import com.g4.dev.esportlancentersw.exception.reserva.ReservaNotContentForTodayException;
import com.g4.dev.esportlancentersw.model.Reserva;
import com.g4.dev.esportlancentersw.repository.ReservaRepository;
import com.g4.dev.esportlancentersw.service.IServices.IClienteService;
import com.g4.dev.esportlancentersw.service.IServices.IOrdenadorService;
import com.g4.dev.esportlancentersw.service.IServices.IReservaService;
import com.g4.dev.esportlancentersw.service.IServices.IUsuarioService;
import com.g4.dev.esportlancentersw.util.UtilDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservaServiceImpl implements IReservaService {
    @Autowired private ReservaRepository reservaRepository;
    @Autowired private IClienteService clienteService;
    @Autowired private IUsuarioService usuarioService;
    @Autowired private IOrdenadorService iOrdenadorService;

    @Override
    public List<Reserva> listarDatos() {
        return null;
    }
    @Override
    public List<Reserva> listarReservasPorFechaActual() {
        List<Reserva> reservasToday = reservaRepository
                .findAll()
                .stream()
                .filter(reserva ->
                        reserva.getFecha().get(Calendar.DAY_OF_MONTH) == UtilDate.getExactlyCalendar().get(Calendar.DAY_OF_MONTH) &&
                                reserva.getFecha().get(Calendar.MONTH) == UtilDate.getExactlyCalendar().get(Calendar.MONTH) )
                .collect(Collectors.toList());
        if (reservasToday.isEmpty()){
            throw  new ReservaNotContentForTodayException();
        }

        return reservasToday ;
    }

    @Override
    public boolean existsById(long id) {
        return reservaRepository.existsById(id);
    }

    @Override
    public Optional<Reserva> buscarEntidad(Long id) {
        Optional<Reserva> reservaFound  = reservaRepository.findById(id);
        if (reservaFound.isEmpty()){
            throw new NotFoundException("Reserva no encontrada");
        }
        return reservaFound;
    }

    @Override
    public Page<Reserva> listarEntidadPorPagina(Pageable p) {
        // TODO: 25/02/2023 implementar el listar por pagina pero filtrando por el dia y mes actual
        return  null;
    }

    @Override
    public Reserva registrarEntidad(Reserva entidad) {
        // TODO: 25/02/2023 Reforzar la implementacion de este método 
        validateReservaInput(entidad);
        entidad.setEstado(true);
        entidad.setFecha(UtilDate.getExactlyCalendar());
        return reservaRepository.save(entidad);
    }

    @Override
    public Reserva modificarEntidad(Reserva entidad) {
        validateReservaInput(entidad);
        entidad.setEstado(true);

        return reservaRepository.save(entidad);
    }

    @Override
    public boolean eliminarEntidad(Long id) {
        Optional<Reserva> reservaFound = buscarEntidad(id);
        if (reservaFound.isEmpty() ||!reservaFound.get().getEstado() ){
            throw  new NotFoundException("No se encontro una reserva para eliminar");
        }
        reservaFound.get().setEstado(false);
        reservaRepository.save(reservaFound.get());
        return true;
    }


    void validateReservaInput(Reserva re){
        if (!usuarioService.existsById(re.getIdUsuario().getId())){
            throw new NotFoundException("Usuario no existente");
        }
        if (!clienteService.existsById(re.getCliente().getIdCliente())){
            throw new NotFoundException("Cliente no existente");
        }
        if (!iOrdenadorService.existsById(re.getIdOrdenador().getIdOrdenador())){
            throw new NotFoundException("Ordenador no existente");
        }

    }

}
