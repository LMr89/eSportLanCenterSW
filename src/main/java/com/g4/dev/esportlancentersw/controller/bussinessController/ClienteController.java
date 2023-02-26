package com.g4.dev.esportlancentersw.controller.bussinessController;


import com.g4.dev.esportlancentersw.DTO.response.SuccessResponseDTO;
import com.g4.dev.esportlancentersw.model.Cliente;
import com.g4.dev.esportlancentersw.service.IServices.IClienteService;
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
@RequestMapping("/clientes")
public class ClienteController {
    private final Logger log = LogManager.getLogger(ClienteController.class);
    private SuccessResponseDTO succe;

    @Autowired private IClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientesActivos()
    {
        log.debug("Listando clientes");
        return  new ResponseEntity<>(clienteService.listarDatos(), HttpStatus.OK);
    }
    @GetMapping("/pag")
    public ResponseEntity<Page<Cliente>> listarClientesPorPagina(Pageable pagClientes){
        log.debug("Listando clientes por pagina");
        return new ResponseEntity<>(clienteService.listarEntidadPorPagina(pagClientes), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> buscarProductoPorId(@PathVariable("id") Long id){
        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.FOUND_SUCCESS,
                clienteService.buscarEntidad(id).get()
        );
        log.debug("Cliente Buscar");
        return ResponseEntity.ok(succe);
    }


    @PostMapping
    public ResponseEntity<SuccessResponseDTO> registrarCliente(@Valid @RequestBody Cliente cliente){
        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.ENTITY_CREATED,
                clienteService.registrarEntidad(cliente)
        );
        log.debug("Cliente Registrar");
        return ResponseEntity.ok(succe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> modificarCliente(@Valid @RequestBody Cliente cliente,
                                                                @PathVariable("id") long id){
        cliente.setIdCliente(id);

        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.MODIFIED_SUCCESS,
                clienteService.modificarEntidad(cliente)
        );
        log.debug("Cliente Modificar");
        return ResponseEntity.ok(succe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> eliminarCliente(@PathVariable("id") Long id){
        succe = SuccessResponseDTO.buildQuickResponse(
                HttpStatus.OK,
                ResponseMessageConstants.DELETED_SUCCESS,
                clienteService.eliminarEntidad(id)
        );
        log.debug("Cliente Eliminar");
        return new ResponseEntity<>(succe, HttpStatus.valueOf(succe.getHttpStatus()));
    }


}
