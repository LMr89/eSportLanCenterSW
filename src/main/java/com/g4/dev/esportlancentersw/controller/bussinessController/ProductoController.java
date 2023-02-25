package com.g4.dev.esportlancentersw.controller.bussinessController;


import com.g4.dev.esportlancentersw.model.Producto;
import com.g4.dev.esportlancentersw.service.IServices.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private IProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductosActivos()
    {
        return  new ResponseEntity<>(productoService.listarDatos(), HttpStatus.OK);
    }
    @GetMapping("/pag")
    public ResponseEntity<Page<Producto>> listarProductosPorPagina(Pageable pagProducto){
        return new ResponseEntity<>(productoService.listarEntidadPorPagina(pagProducto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarProductoPorId(@PathVariable("id") Long id){
        return new ResponseEntity<>(productoService.buscarEntidad(id).get(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Producto> registrarProducto(@Valid @RequestBody Producto pro){
        return new ResponseEntity<>(productoService.registrarEntidad(pro), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> modificarProducto(@Valid @RequestBody Producto pro,
                                                      @PathVariable("id") long id){
        pro.setIdProducto(id);
        return new ResponseEntity<>(productoService.modificarEntidad(pro), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarProducto(@PathVariable("id") Long id){
        return  new ResponseEntity<>(productoService.eliminarEntidad(id), HttpStatus.OK);
    }

}
