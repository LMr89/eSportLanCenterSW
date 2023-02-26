package com.g4.dev.esportlancentersw.service.ServicesImpl;

import com.g4.dev.esportlancentersw.exception.common.CorreoRepeatedException;
import com.g4.dev.esportlancentersw.exception.common.DniRepeatedException;
import com.g4.dev.esportlancentersw.exception.common.NotFoundException;
import com.g4.dev.esportlancentersw.exception.common.TelephoneRepeatedException;
import com.g4.dev.esportlancentersw.exception.cliente.ClienteNameExistsException;
import com.g4.dev.esportlancentersw.model.Cliente;
import com.g4.dev.esportlancentersw.repository.ClienteRepository;
import com.g4.dev.esportlancentersw.service.IServices.IClienteService;
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
public class ClienteServiceImpl implements IClienteService {


    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarDatos() {
        return clienteRepository
                .findAll()
                .stream()
                .filter(Cliente::getEstado)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cliente> buscarEntidad(Long id) {
        Optional<Cliente> clientFound  =  clienteRepository.findById(id);
        if (clientFound.isEmpty()){
            throw  new NotFoundException("Cliente no Encontrado");
        }
        return clientFound;
    }

    @Override
    public Page<Cliente> listarEntidadPorPagina(Pageable p) {
        return clienteRepository.findAll(p);
    }

    @Override
    public Cliente registrarEntidad(Cliente entidad) {
        validateClienteInput(entidad);
        entidad.setEstado(true);
        return clienteRepository.save(entidad);
    }

    @Override
    public Cliente modificarEntidad(Cliente entidad) {
        if (buscarEntidad(entidad.getIdCliente()).isEmpty()){
            throw  new NotFoundException("Cliente no encontrado");
        }
        entidad.setEstado(true);
        return clienteRepository.save(entidad);
    }

    @Override
    public boolean eliminarEntidad(Long id) {
        Optional<Cliente> clientFound =buscarEntidad(id);
        if (clientFound.isEmpty() || !clientFound.get().getEstado()){
            throw  new NotFoundException("Cliente no encontrado");
        }

        clientFound.get().setEstado(false);
        clienteRepository.save(clientFound.get());
        return true;
    }


    private void validateClienteInput(Cliente entidad){

        if (clienteRepository.existsByCorreo(entidad.getCorreo())){
            throw  new CorreoRepeatedException();
        }
        if (clienteRepository.existsByDni(entidad.getDni())){
            throw  new DniRepeatedException();
        }
        if (clienteRepository.existsByTelefono(entidad.getTelefono())){
            throw  new TelephoneRepeatedException();
        }
        if (clienteRepository.existsByNomUsuario(entidad.getNomUsuario())){
            throw  new ClienteNameExistsException();
        }
    }
}
