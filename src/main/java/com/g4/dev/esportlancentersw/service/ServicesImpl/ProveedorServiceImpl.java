package com.g4.dev.esportlancentersw.service.ServicesImpl;

import com.g4.dev.esportlancentersw.exception.NotFoundException;
import com.g4.dev.esportlancentersw.exception.proveedor.ProveedorNomContactoRepeatedException;
import com.g4.dev.esportlancentersw.model.Proveedor;
import com.g4.dev.esportlancentersw.repository.ProveedorRepository;
import com.g4.dev.esportlancentersw.service.IServices.IProveedorService;
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
public class ProveedorServiceImpl implements IProveedorService {
    @Autowired private ProveedorRepository proveedorRepository;


    @Override
    public List<Proveedor> listarDatos() {
        return proveedorRepository.findAll()
                .stream()
                .filter(Proveedor::isEstado)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Proveedor> buscarEntidad(Long id) {
        Optional<Proveedor> supplierFound = proveedorRepository.findById(id);
        if (supplierFound.isEmpty()){
            throw  new NotFoundException("Proveedor no encontrado");
        }
        return supplierFound;
    }

    @Override
    public Page<Proveedor> listarEntidadPorPagina(Pageable p) {
        return proveedorRepository.findAll(p);
    }

    @Override
    public Proveedor registrarEntidad(Proveedor entidad) {
        validateExistentSupplier(entidad.getNomContacto());
        entidad.setEstado(true);
        proveedorRepository.save(entidad);
        return entidad;
    }

    @Override
    public Proveedor modificarEntidad(Proveedor entidad) {
        buscarEntidad(entidad.getIdProveedor());
        entidad.setEstado(true);
        return  proveedorRepository.save(entidad);
    }

    @Override
    public boolean eliminarEntidad(Long id) {
        Optional<Proveedor> supplierFound = buscarEntidad(id);
        if (!supplierFound.get().isEstado()){
            throw  new NotFoundException("Proveedor no encontrado");
        }

        supplierFound.ifPresent(proveedor -> {
            proveedor.setEstado(false);
            proveedorRepository.save(proveedor);
        });
        return true;
    }

    void validateExistentSupplier(String nomContacto){
        if(proveedorRepository.existsByNomContacto(nomContacto)){
            throw  new ProveedorNomContactoRepeatedException();
        }
    }
}
