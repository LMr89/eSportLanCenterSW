package com.g4.dev.esportlancentersw.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IMainService <T> {
    List<T> listarDatos();
    Optional<T> buscarEntidad(Long id);
    Page<T> listarEntidadPorPagina(Pageable p );
    T registrarEntidad(T entidad);
    T modificarEntidad(T entidad);
    boolean eliminarEntidad(Long id);

}
