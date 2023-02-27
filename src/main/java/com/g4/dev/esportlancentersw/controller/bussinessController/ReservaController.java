package com.g4.dev.esportlancentersw.controller.bussinessController;

import com.g4.dev.esportlancentersw.DTO.response.SuccessResponseDTO;
import com.g4.dev.esportlancentersw.exception.common.NotImpletedException;
import com.g4.dev.esportlancentersw.model.Cliente;
import com.g4.dev.esportlancentersw.model.Reserva;
import com.g4.dev.esportlancentersw.service.IServices.IClienteService;
import com.g4.dev.esportlancentersw.service.IServices.IReservaService;
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
@RequestMapping("/reservas")
public class ReservaController {
    private final Logger log = LogManager.getLogger(ClienteController.class);
    private SuccessResponseDTO succe;

    @Autowired
    private IReservaService reservaService;

    @GetMapping
    public ResponseEntity<Object> listarReservasActuales()
    {
        log.debug("Listando Reserva ");
        return  new ResponseEntity<>(reservaService.listarReservasPorFechaActual(), HttpStatus.OK);
    }
    @GetMapping("/pag")
    public ResponseEntity<Page<Reserva>> listarClientesPorPagina(Pageable pagReserva){
        log.debug("Listando clientes por pagina");
        return new ResponseEntity<>(reservaService.listarEntidadPorPagina(pagReserva), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> buscarReservaPorId(@PathVariable("id") Long id){
        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.FOUND_SUCCESS,
                reservaService.buscarEntidad(id).get()
        );
        log.debug("Reserva Buscar");
        return ResponseEntity.ok(succe);
    }


    @PostMapping
    public ResponseEntity<SuccessResponseDTO> registrarReserva(@Valid @RequestBody Reserva reserva){
        succe = SuccessResponseDTO.buildQuickResponse(
                HttpStatus.CREATED,
                ResponseMessageConstants.ENTITY_CREATED,
                reservaService.registrarEntidad(reserva)
        );
        log.debug("Reserva Registrar");
        return new ResponseEntity<>(succe, HttpStatus.valueOf(succe.getHttpStatus()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> modificarReserva(@Valid @RequestBody Reserva reserva,
                                                               @PathVariable("id") long id){
        throw new NotImpletedException();
        // TODO: 26/02/2023 Implementarlo para el segundo sprint
        /*reserva.setIdReserva(id);

        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.MODIFIED_SUCCESS,
                reservaService.modificarEntidad(reserva)
        );
        log.debug("Reserva Modificar");*/
        
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> eliminarCliente(@PathVariable("id") Long id){
        succe = SuccessResponseDTO.buildQuickResponse(
                HttpStatus.OK,
                ResponseMessageConstants.DELETED_SUCCESS,
                reservaService.eliminarEntidad(id)
        );
        log.debug("Reserva Eliminar");
        return new ResponseEntity<>(succe, HttpStatus.valueOf(succe.getHttpStatus()));
    }

}
