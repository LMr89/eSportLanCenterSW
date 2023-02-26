package com.g4.dev.esportlancentersw.controller.bussinessController;

import com.g4.dev.esportlancentersw.DTO.response.SuccessResponseDTO;
import com.g4.dev.esportlancentersw.model.Cliente;
import com.g4.dev.esportlancentersw.model.Proveedor;
import com.g4.dev.esportlancentersw.service.IServices.IProveedorService;
import com.g4.dev.esportlancentersw.util.ResponseMessageConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
    private final Logger log = LogManager.getLogger(ProveedorController.class);
    private SuccessResponseDTO succe;

    @Autowired
    private IProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<Proveedor>> listarProveedoresActivos()
    {
        log.debug("Listando Proveedores");
        return  new ResponseEntity<>(proveedorService.listarDatos(), HttpStatus.OK);
    }
    @GetMapping("/pag")
    public ResponseEntity<Page<Proveedor>> listarProveedorPorPagina(Pageable pagProveedores){
        log.debug("Listando Proveedor por pagina");
        return new ResponseEntity<>(proveedorService.listarEntidadPorPagina(pagProveedores), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> buscarProveedorPorId(@PathVariable("id") Long id){
        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.FOUND_SUCCESS,
                proveedorService.buscarEntidad(id).get()
        );
        log.debug("Proveedor Buscar");
        return ResponseEntity.ok(succe);
    }


    @PostMapping
    public ResponseEntity<SuccessResponseDTO> registrarCliente(@Valid @RequestBody Proveedor proveedor){
        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.ENTITY_CREATED,
                proveedorService.registrarEntidad(proveedor)
        );
        log.debug("Proveedor Registrar");
        return ResponseEntity.ok(succe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> modificarCliente(@Valid @RequestBody Proveedor proveedor,
                                                               @PathVariable("id") long id){
        proveedor.setIdProveedor(id);

        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.MODIFIED_SUCCESS,
                proveedorService.modificarEntidad(proveedor)
        );
        log.debug("Proveedor Modificar");
        return ResponseEntity.ok(succe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> eliminarCliente(@PathVariable("id") Long id){
        succe = SuccessResponseDTO.buildQuickResponse(
                HttpStatus.OK,
                ResponseMessageConstants.DELETED_SUCCESS,
                proveedorService.eliminarEntidad(id)
        );
        log.debug("Proveedor Eliminar");
        return new ResponseEntity<>(succe, HttpStatus.valueOf(succe.getHttpStatus()));
    }


}
