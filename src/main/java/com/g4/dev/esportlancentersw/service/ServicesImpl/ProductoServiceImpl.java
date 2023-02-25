package com.g4.dev.esportlancentersw.service.ServicesImpl;

import com.g4.dev.esportlancentersw.exception.producto.ProductoNotFoundException;
import com.g4.dev.esportlancentersw.model.Producto;
import com.g4.dev.esportlancentersw.repository.ProductoRepository;
import com.g4.dev.esportlancentersw.service.IServices.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements IProductoService {
    @Autowired
    private ProductoRepository productoRepository;


    @Override
    public List<Producto> listarDatos() {
        return productoRepository
                .findAll()
                .stream()
                .filter(Producto::isEstado)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Producto> buscarEntidad(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Page<Producto> listarEntidadPorPagina(Pageable p) {
        return productoRepository.findAll(p);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public Producto registrarEntidad(Producto entidad) {
        entidad.setEstado(true);
        return productoRepository.save(entidad);
    }

    @Override
    public Producto modificarEntidad(Producto entidad) {
        Optional<Producto> encontrarProducto = buscarEntidad(entidad.getIdProducto());
        if (encontrarProducto.isEmpty()){
            throw new ProductoNotFoundException("Producto no encontrado");

        }
        entidad.setEstado(true);
        return productoRepository.save(entidad);
    }

    @Override
    public boolean eliminarEntidad(Long id) {
        Optional<Producto> encontrarProducto = buscarEntidad(id);
        if (encontrarProducto.isEmpty()){
            throw new ProductoNotFoundException("Producto no encontrado");

        }

        encontrarProducto.get().setEstado(false);
        productoRepository.save(encontrarProducto.get());
        return true;
    }
}
