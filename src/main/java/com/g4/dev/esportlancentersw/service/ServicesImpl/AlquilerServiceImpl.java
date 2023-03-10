package com.g4.dev.esportlancentersw.service.ServicesImpl;

import com.g4.dev.esportlancentersw.exception.alquiler.AlquilerNotEqualsForTodayException;
import com.g4.dev.esportlancentersw.exception.common.NotFoundException;
import com.g4.dev.esportlancentersw.exception.reserva.ReservaNotEqualsForTodayException;
import com.g4.dev.esportlancentersw.model.Alquiler;
import com.g4.dev.esportlancentersw.repository.AlquilerRepository;
import com.g4.dev.esportlancentersw.service.IServices.IAlquilerService;
import com.g4.dev.esportlancentersw.service.IServices.IClienteService;
import com.g4.dev.esportlancentersw.service.IServices.IOrdenadorService;
import com.g4.dev.esportlancentersw.util.UtilDate;
import org.apache.commons.lang.time.DateUtils;
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
public class AlquilerServiceImpl implements IAlquilerService {

    @Autowired private AlquilerRepository alquilerRepository;

    @Autowired private IClienteService clienteService;
    @Autowired private IOrdenadorService iOrdenadorService;
    /**
     * Método que retorna los alquiler de la fecha actual
     * @return  lista de alquiler de hoy
     * @author Luis DEV
     * @since 1.0
     */
    @Override
    public List<Alquiler> listarDatos() {
        return alquilerRepository
                .findAll()
                .stream()
                .filter(alquiler ->
                        alquiler.getFecha().get(Calendar.DAY_OF_MONTH) == UtilDate.getExactlyCalendar().get(Calendar.DAY_OF_MONTH) &&
                                alquiler.getFecha().get(Calendar.MONTH) == UtilDate.getExactlyCalendar().get(Calendar.MONTH)
                            && alquiler.getEstado())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Alquiler> buscarEntidad(Long id) {
        Optional<Alquiler> alquiler = alquilerRepository.findById(id);

        if (alquiler.isEmpty()){
            throw  new NotFoundException("Alquiler no encontrado con ese ID");
        }
        return alquiler;
    }

    @Override
    public Page<Alquiler> listarEntidadPorPagina(Pageable p) {
        return alquilerRepository.findAll(p);
    }

    @Override
    public Alquiler registrarEntidad(Alquiler entidad) {
        verifyCorrectAlquiler(entidad);
        entidad.setEstado(true);
        return alquilerRepository.save(entidad);
    }


    /**
     * Método no implementado debido a que solo se registran y anulan alquileres
     * @param entidad representa el objeto Alquiler
     * @return null
     * @author Luis DEV
     * @since 1.0
     */
    @Override
    public Alquiler modificarEntidad(Alquiler entidad) {

        return null;
    }

    @Override
    public boolean eliminarEntidad(Long id) {
        Optional<Alquiler> alquiler = buscarEntidad(id);
        if (!alquiler.get().getEstado()){
            throw  new NotFoundException("No se encontro el alquiler");
        }
        alquiler.get().setEstado(false);
        alquilerRepository.save(alquiler.get());
        return true;
    }

    void verifyCorrectAlquiler(Alquiler entidad){
        if (!clienteService.existsById(entidad.getIdCliente().getIdCliente())){
            throw new NotFoundException("Cliente no existente");
        }
        if (!iOrdenadorService.existsById(entidad.getIdOrdenador().getIdOrdenador())){
            throw new NotFoundException("Ordenador no existente");
        }
        if (!compareCalendarByDay(entidad.getFecha())){
            throw  new AlquilerNotEqualsForTodayException();
        }
    }
    boolean compareCalendarByDay(Calendar cal){
        boolean isAccepted = true;
        Calendar calToday =  UtilDate.getExactlyCalendar();
        if (!DateUtils.isSameDay(cal, calToday)){
            isAccepted = false;
        }

        return  isAccepted;
    }

}
