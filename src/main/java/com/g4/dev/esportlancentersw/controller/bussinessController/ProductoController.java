package com.g4.dev.esportlancentersw.controller.bussinessController;


import com.g4.dev.esportlancentersw.DTO.response.SuccessResponseDTO;
import com.g4.dev.esportlancentersw.model.Producto;
import com.g4.dev.esportlancentersw.service.IServices.IProductoService;
import com.g4.dev.esportlancentersw.util.ResponseMessageConstants;
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
    private  SuccessResponseDTO succe;
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
    public ResponseEntity<SuccessResponseDTO> buscarProductoPorId(@PathVariable("id") Long id){
        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.FOUND_SUCCESS,
                productoService.buscarEntidad(id).get()
        );
        return ResponseEntity.ok(succe);
    }


    @PostMapping
    public ResponseEntity<SuccessResponseDTO> registrarProducto(@Valid @RequestBody Producto pro){
        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.ENTITY_CREATED,
                productoService.registrarEntidad(pro)
        );
        return ResponseEntity.ok(succe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> modificarProducto(@Valid @RequestBody Producto pro,
                                                      @PathVariable("id") long id){
        pro.setIdProducto(id);

        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.MODIFIED_SUCCESS,
                productoService.modificarEntidad(pro)
        );
        return ResponseEntity.ok(succe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> eliminarProducto(@PathVariable("id") Long id){
        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.DELETED_SUCCESS,
                productoService.eliminarEntidad(id)
        );
        return ResponseEntity.ok(succe);
    }

}
