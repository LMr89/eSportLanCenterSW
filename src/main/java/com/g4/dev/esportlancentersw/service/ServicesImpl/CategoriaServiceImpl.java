package com.g4.dev.esportlancentersw.service.ServicesImpl;

import com.g4.dev.esportlancentersw.model.Categoria;
import com.g4.dev.esportlancentersw.repository.CategoriaRepository;
import com.g4.dev.esportlancentersw.service.IServices.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// TODO: 25/02/2023 Terminar el servicio de categoria

@Service
@Transactional
public class CategoriaServiceImpl implements ICategoriaService {
    @Autowired private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listarDatos() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> buscarEntidad(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<Categoria> listarEntidadPorPagina(Pageable p) {
        return null;
    }

    @Override
    public Categoria registrarEntidad(Categoria entidad) {
        return null;
    }

    @Override
    public Categoria modificarEntidad(Categoria entidad) {
        return null;
    }

    @Override
    public boolean eliminarEntidad(Long id) {
        return false;
    }
}
