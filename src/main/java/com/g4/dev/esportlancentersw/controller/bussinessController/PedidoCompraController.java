package com.g4.dev.esportlancentersw.controller.bussinessController;

import com.g4.dev.esportlancentersw.DTO.response.SuccessResponseDTO;
import com.g4.dev.esportlancentersw.model.Cliente;
import com.g4.dev.esportlancentersw.model.PedidoCompra;
import com.g4.dev.esportlancentersw.service.IServices.IClienteService;
import com.g4.dev.esportlancentersw.service.IServices.IPedidoCompraService;
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
@RequestMapping("/pedidosCompras")
public class PedidoCompraController  {

    private  Logger log = LogManager.getLogger(PedidoCompraController.class);
    private SuccessResponseDTO succe;

    @Autowired
    private IPedidoCompraService pedidoCompraService;

    @GetMapping
    public ResponseEntity<List<PedidoCompra>> listarPedidoComprasActivos()
    {
        log.info("Listando PedidoCompras");
        return  new ResponseEntity<>(pedidoCompraService.listarDatos(), HttpStatus.OK);
    }
    @GetMapping("/pag")
    public ResponseEntity<Page<PedidoCompra>> listarPedidoComprasPorPagina(Pageable pagPedidoCompra){
        log.debug("Listando PedidoCompras por pagina");
        return new ResponseEntity<>(pedidoCompraService.listarEntidadPorPagina(pagPedidoCompra), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> buscarPedidoComprasPorId(@PathVariable("id") Long id){
        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.FOUND_SUCCESS,
                pedidoCompraService.buscarEntidad(id).get()
        );
        log.debug("PedidoCompras Buscar");
        return ResponseEntity.ok(succe);
    }


    @PostMapping
    public ResponseEntity<SuccessResponseDTO> registrarPedidoCompra(@Valid @RequestBody PedidoCompra pedidoCompra){
        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.ENTITY_CREATED,
                pedidoCompraService.registrarEntidad(pedidoCompra)
        );
        log.debug("PedidoCompra Registrar");
        return ResponseEntity.ok(succe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> modificarCliente(@Valid @RequestBody PedidoCompra pedidoCompra,
                                                               @PathVariable("id") long id){
        pedidoCompra.setIdCompra(id);

        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.MODIFIED_SUCCESS,
                pedidoCompraService.modificarEntidad(pedidoCompra)
        );
        log.debug("PedidoCompra Modificar");
        return ResponseEntity.ok(succe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> eliminarPedidoCompra(@PathVariable("id") Long id){
        succe = SuccessResponseDTO.buildQuickResponse(
                HttpStatus.OK,
                ResponseMessageConstants.DELETED_SUCCESS,
                pedidoCompraService.eliminarEntidad(id)
        );
        log.debug("PedidoCompra Eliminar");
        return new ResponseEntity<>(succe, HttpStatus.valueOf(succe.getHttpStatus()));
    }


}
