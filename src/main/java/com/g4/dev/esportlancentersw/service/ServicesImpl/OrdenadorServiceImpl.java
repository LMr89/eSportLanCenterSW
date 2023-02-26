package com.g4.dev.esportlancentersw.service.ServicesImpl;

import com.g4.dev.esportlancentersw.exception.common.NotFoundException;
import com.g4.dev.esportlancentersw.exception.ordenador.OrdenadorAlReadyInMaintenanceException;
import com.g4.dev.esportlancentersw.exception.ordenador.OrdenadorIpAddressRepeatedException;
import com.g4.dev.esportlancentersw.exception.ordenador.OrdenadorNameRepearedException;
import com.g4.dev.esportlancentersw.exception.ordenador.OrdenadorNumRepeatedException;
import com.g4.dev.esportlancentersw.model.Ordenador;
import com.g4.dev.esportlancentersw.repository.OrdenadorRepository;
import com.g4.dev.esportlancentersw.service.IServices.IOrdenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class OrdenadorServiceImpl implements IOrdenadorService {

    @Autowired private OrdenadorRepository ordenadorRepository;

    @Override
    public List<Ordenador> listarDatos() {
        return ordenadorRepository
                .findAll()
                .stream()
                .filter(Ordenador::getEstado)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ordenador> ordenadoresEnMantenimiento() {
        return ordenadorRepository
                .findAll()
                .stream()
                .filter(Ordenador::getMantenimiento)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(long id) {
        return ordenadorRepository.existsById(id);
    }

    @Override
    public Optional<Ordenador> buscarEntidad(Long id) {
        Optional<Ordenador> foundedMachine = ordenadorRepository.findById(id);
        if (foundedMachine.isEmpty()){
            throw  new NotFoundException("Ordenador no encontrado");
        }

        return foundedMachine;
    }

    @Override
    public Page<Ordenador> listarEntidadPorPagina(Pageable p) {
        return ordenadorRepository.findAll(p);
    }

    @Override
    public Ordenador registrarEntidad(Ordenador entidad) {
        validateOrdenadorInput(entidad);

        entidad.setEstado(true);
        entidad.setMantenimiento(false);
        return ordenadorRepository.save(entidad);
    }

    @Override
    public Ordenador modificarEntidad(Ordenador entidad) {
        if (!ordenadorRepository.existsById(entidad.getIdOrdenador())){
            throw  new NotFoundException("Ordenador no encontrado");
        }

        entidad.setEstado(true);
        entidad.setMantenimiento(false);
        return ordenadorRepository.save(entidad);
    }


    @Override
    public boolean eliminarEntidad(Long id) {
        Optional<Ordenador> ordenadorFound =  buscarEntidad(id);
        if (!ordenadorFound.get().getEstado()){
            throw  new NotFoundException("Ordenador no encontrado");
        }

        ordenadorFound.get().setEstado(false);
        ordenadorRepository.save(ordenadorFound.get());
        return true;
    }


    void validateOrdenadorInput(Ordenador ord){
        if (ordenadorRepository.existsByIpOrdenador(ord.getIpOrdenador())){
            throw new OrdenadorIpAddressRepeatedException();
        }
        if (ordenadorRepository.existsByNumOrdenador(ord.getNumOrdenador())){
            throw new OrdenadorNumRepeatedException();
        }
        if (ordenadorRepository.existsByNomDispositivo(ord.getNomDispositivo())){
            throw new OrdenadorNameRepearedException();
        }
    }


    @Override
    public void ponerEnMantenimientoOrdenador(long id) {
        Optional<Ordenador> ord = buscarEntidad(id);
        if (ord.get().getMantenimiento()){
            throw new OrdenadorAlReadyInMaintenanceException();
        }

        ord.get().setMantenimiento(true);
        ord.get().setEstado(false);
        ordenadorRepository.save(ord.get());
    }

    @Override
    public void levantarMantenimiento(long id) {
        Optional<Ordenador> ord = buscarEntidad(id);
        if (!ord.get().getMantenimiento()){
            throw new OrdenadorAlReadyInMaintenanceException("El ordenador ya no se encuenta en mantenimiento");
        }

        ord.get().setMantenimiento(false);
        ord.get().setEstado(true);
        ordenadorRepository.save(ord.get());
    }


}
