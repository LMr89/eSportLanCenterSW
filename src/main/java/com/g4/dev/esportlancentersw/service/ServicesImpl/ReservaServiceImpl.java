package com.g4.dev.esportlancentersw.service.ServicesImpl;

import com.g4.dev.esportlancentersw.exception.common.NotFoundException;
import com.g4.dev.esportlancentersw.exception.common.NotImpletedException;
import com.g4.dev.esportlancentersw.exception.reserva.ReservaIsNotFreeException;
import com.g4.dev.esportlancentersw.exception.reserva.ReservaNotContentForTodayException;
import com.g4.dev.esportlancentersw.exception.reserva.ReservaNotEqualsForTodayException;
import com.g4.dev.esportlancentersw.exception.reserva.ReservaNotInWorkingHoursException;
import com.g4.dev.esportlancentersw.model.Reserva;
import com.g4.dev.esportlancentersw.repository.ReservaRepository;
import com.g4.dev.esportlancentersw.service.IServices.IClienteService;
import com.g4.dev.esportlancentersw.service.IServices.IOrdenadorService;
import com.g4.dev.esportlancentersw.service.IServices.IReservaService;
import com.g4.dev.esportlancentersw.service.IServices.IUsuarioService;
import com.g4.dev.esportlancentersw.util.UtilDate;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
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
                        reserva.getFechaInicio().get(Calendar.DAY_OF_MONTH) == UtilDate.getExactlyCalendar().get(Calendar.DAY_OF_MONTH) &&
                                reserva.getFechaInicio().get(Calendar.MONTH) == UtilDate.getExactlyCalendar().get(Calendar.MONTH) )
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
        validateReservaInput(entidad);
        addMinutesToFechaInicioForFechaFin(entidad);
        entidad.setEstado(true);
        return reservaRepository.save(entidad);
    }

    @Override
    public Reserva modificarEntidad(Reserva entidad) {

        //validateReservaInput(entidad);
        //entidad.setEstado(true);

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

        if (!compareCalendarByDay(re.getFechaInicio())){
            throw  new ReservaNotEqualsForTodayException();
        }
        if (!compareCalendarByHour(re.getFechaInicio())){
            throw new ReservaNotInWorkingHoursException();
        }
        if (!verifyStartAndDate(re)){
            throw new ReservaIsNotFreeException();
        }

    }

    /**
     * Método encargado de setear la fecha fin clonando la fecha de inicio y aumentandole
     * el tiempo dado por el cliente
     * @param re
     * @return the amount of health hero has after attack
     * @author Luis DEV
     * @since 1.0
     */
    void addMinutesToFechaInicioForFechaFin(Reserva re){
        Calendar calStrart = (Calendar) re.getFechaInicio().clone();
        calStrart.add(Calendar.MINUTE, re.getTiempo());
        re.setFechaFin(calStrart);
    }

    /**
     * Metodo encargado de verficar si la fecha de inicio de la reserva actual
     * no debe ser igual a las fecha de inicio y fin de un registro con el mismo
     * ordenador
     * @param re Reserva entidad
     * @return the amount of health hero has after attack
     * @author Luis DEV
     * @since 1.0
     */
    Boolean verifyStartAndDate(Reserva re){
        boolean isAllOk = true;
        List<Reserva> reservasFromSP = reservaRepository
                .getReservasByStoreProcedure(
                        re.getIdOrdenador().getIdOrdenador(),
                        new SimpleDateFormat("yyyy-MM-dd")
                                .format(re.getFechaInicio().getTime())
                );

        //reservasFromSP.forEach(System.out::println);
        if (reservasFromSP.size() >= 1){
            for(Reserva reserva : reservasFromSP){
                if (UtilDate.isCalendarBetween
                        (reserva.getFechaInicio(), reserva.getFechaFin(), re.getFechaInicio())){
                    isAllOk = false;
                    break;
                }

                if (reserva.getFechaInicio().get(Calendar.MINUTE) == re.getFechaInicio().get(Calendar.MINUTE)
                 &&  reserva.getFechaInicio().get(Calendar.HOUR_OF_DAY) == re.getFechaInicio().get(Calendar.HOUR_OF_DAY)) {
                    isAllOk = false;

                    break;
                }
                if (reserva.getFechaFin().get(Calendar.MINUTE) == re.getFechaInicio().get(Calendar.MINUTE)
                        &&  reserva.getFechaFin().get(Calendar.HOUR_OF_DAY) == re.getFechaInicio().get(Calendar.HOUR_OF_DAY)) {
                    isAllOk = false;

                    break;
                }

            }
            System.out.println(isAllOk);
        }
        return  isAllOk;

    }

    boolean compareCalendarByDay(Calendar cal){
        boolean isAccepted = true;
        Calendar calToday =  UtilDate.getExactlyCalendar();
        if (!DateUtils.isSameDay(cal, calToday)){
            isAccepted = false;
        }

        return  isAccepted;
    }

    /**
     * Método encargado de comparar las horas de los objetos calendar
     * si este esta dentro del rango 8AM - 22PM sino es asi ,lanza una excepcion
     * formato 24 horas
     * @param cal
     * @return isAccepted
     * @author Luis DEV
     * @since 1.0
     */
    boolean compareCalendarByHour(Calendar cal){
        boolean isAccepted = true;

        int hour =  cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        if (hour < 9 ||(hour == 22 && minute >=0)){
            isAccepted = false;
        }
        return  isAccepted;
    }

}
