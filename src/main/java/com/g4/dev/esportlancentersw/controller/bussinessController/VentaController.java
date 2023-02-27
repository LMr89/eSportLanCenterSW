package com.g4.dev.esportlancentersw.controller.bussinessController;

import com.g4.dev.esportlancentersw.DTO.response.SuccessResponseDTO;
import com.g4.dev.esportlancentersw.mapper.venta.VentaMapper;
import com.g4.dev.esportlancentersw.model.Cliente;
import com.g4.dev.esportlancentersw.model.Venta;
import com.g4.dev.esportlancentersw.service.IServices.IVentaService;
import com.g4.dev.esportlancentersw.util.ResponseMessageConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {
    private final Logger log = LogManager.getLogger(ClienteController.class);
    private SuccessResponseDTO succe;

    @Autowired private IVentaService ventaService;
    @Autowired  HttpServletRequest req;

    @GetMapping
    public ResponseEntity<List<Venta>> listarVentasActivos(final HttpServletRequest req)
    {
        log.debug("Listando Ventas");
        System.out.println(req.getRequestURL());
        System.out.println(req.getRequestURI());
        System.out.println(req.getRemoteHost());
        System.out.println(req.getContextPath());
        System.out.println(req.getServletPath());
        System.out.println(req.getServerName());
        System.out.println(req.getLocalAddr());
        return  new ResponseEntity<>(ventaService.listarDatos(), HttpStatus.OK);
    }
    @GetMapping("/pag")
    public ResponseEntity<Page<Venta>> listarClientesPorPagina(Pageable pagVentas){
        log.debug("Listando Venta por pagina");
        return new ResponseEntity<>(ventaService.listarEntidadPorPagina(pagVentas), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> buscarVentaPorId(@PathVariable("id") Long id){
        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.FOUND_SUCCESS,
                ventaService.buscarEntidad(id).get()
        );
        log.debug("Venta Buscar");
        return ResponseEntity.ok(succe);
    }

    @PostMapping
    public ResponseEntity<SuccessResponseDTO> registrarVenta(@Valid @RequestBody Venta venta ){
        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.ENTITY_CREATED,
                VentaMapper.mapEntidatToDTO(ventaService.registrarEntidad(venta),req)
        );
        log.debug("Venta Registrar");
        return ResponseEntity.ok(succe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> modificarCliente(@Valid @RequestBody Venta venta,
                                                               @PathVariable("id") long id){
        venta.setIdVenta(id);

        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.MODIFIED_SUCCESS,
                ventaService.modificarEntidad(venta)
        );
        log.debug("Venta Modificar");
        return ResponseEntity.ok(succe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> eliminarVenta(@PathVariable("id") Long id){
        succe = SuccessResponseDTO.buildQuickResponse(
                HttpStatus.OK,
                ResponseMessageConstants.DELETED_SUCCESS,
                ventaService.eliminarEntidad(id)
        );
        log.debug("Venta Eliminar");
        return new ResponseEntity<>(succe, HttpStatus.valueOf(succe.getHttpStatus()));
    }
}
