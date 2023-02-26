package com.g4.dev.esportlancentersw.controller.bussinessController;

import com.g4.dev.esportlancentersw.DTO.response.SuccessResponseDTO;
import com.g4.dev.esportlancentersw.model.Cliente;
import com.g4.dev.esportlancentersw.model.Ordenador;
import com.g4.dev.esportlancentersw.service.IServices.IOrdenadorService;
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
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/ordenadores")
public class OrdenadorController {
    private final Logger log = LogManager.getLogger(OrdenadorController.class);
    private SuccessResponseDTO succe;

    @Autowired
    private IOrdenadorService ordenadorService;

    @GetMapping
    public ResponseEntity<List<Ordenador>> listarOrdenadoresActivos()
    {
        log.debug("Listando Ordenador");
        return  new ResponseEntity<>(ordenadorService.listarDatos(), HttpStatus.OK);
    }
    @GetMapping("/mantenimiento")
    public ResponseEntity<List<Ordenador>> listarOrdenadoresEnMantenimiento()
    {
        log.debug("Listando Ordenador en mantenimiento");
        return  new ResponseEntity<>(ordenadorService.ordenadoresEnMantenimiento(), HttpStatus.OK);
    }

    @GetMapping("/pag")
    public ResponseEntity<Page<Ordenador>> listarOrdenadoresPorPagina(Pageable pagOrdenadores){
        log.debug("Listando Ordenador por pagina");
        return new ResponseEntity<>(ordenadorService.listarEntidadPorPagina(pagOrdenadores), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> buscarOrdenadoresPorId(@PathVariable("id") Long id){
        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.FOUND_SUCCESS,
                ordenadorService.buscarEntidad(id).get()
        );
        log.debug("Ordenador Buscar");
        return ResponseEntity.ok(succe);
    }



    @PostMapping
    public ResponseEntity<SuccessResponseDTO> registrarOrdenador(@Valid @RequestBody Ordenador ordenador){
        succe = SuccessResponseDTO.buildQuickResponse(
                HttpStatus.CREATED,
                ResponseMessageConstants.ENTITY_CREATED,
                ordenadorService.registrarEntidad(ordenador)
        );
        log.debug("Ordenador Registrar");
        return new ResponseEntity<>(succe, HttpStatus.valueOf(succe.getHttpStatus()));
    }

    @PostMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> registrarOrdenadorEnMantenimiento(
            @PathVariable("id")Long id ,
            @RequestParam("mantenimiento") boolean isInMantenimiento){

        String msg  ="";
        if (isInMantenimiento){
            //Poner en mantenimiento
            ordenadorService.ponerEnMantenimientoOrdenador(id);
            msg = "Ordenador puesto en mantenimiento exitosamente";
        }else{
            ordenadorService.levantarMantenimiento(id);
            msg = "Ordenador puesto fuera de mantenimiento exitosamente";
        }


        succe = SuccessResponseDTO.buildQuickResponse(
                HttpStatus.OK
                ,
                ResponseMessageConstants.ENTITY_CREATED,
                msg

        );
        log.debug("Ordenador en mantenimiento");
        return new ResponseEntity<>(succe, HttpStatus.valueOf(succe.getHttpStatus()));
    }



    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> modificarOrdenador(@Valid @RequestBody Ordenador ordenador,
                                                               @PathVariable("id") long id){
        ordenador.setIdOrdenador(id);

        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.MODIFIED_SUCCESS,
                ordenadorService.modificarEntidad(ordenador)
        );
        log.debug("Ordenador Modificar");
        return ResponseEntity.ok(succe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> eliminarOrdenador(@PathVariable("id") Long id){
        succe = SuccessResponseDTO.buildQuickResponse(
                HttpStatus.OK,
                ResponseMessageConstants.DELETED_SUCCESS,
                ordenadorService.eliminarEntidad(id)
        );
        log.debug("Ordenador Eliminar");
        return new ResponseEntity<>(succe, HttpStatus.valueOf(succe.getHttpStatus()));
    }


}
