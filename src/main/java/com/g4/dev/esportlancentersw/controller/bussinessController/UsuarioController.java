package com.g4.dev.esportlancentersw.controller.bussinessController;

import com.g4.dev.esportlancentersw.DTO.response.SuccessResponseDTO;
import com.g4.dev.esportlancentersw.model.Cliente;
import com.g4.dev.esportlancentersw.security.model.Usuario;
import com.g4.dev.esportlancentersw.security.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {
    private final Logger log = LogManager.getLogger(ClienteController.class);
    private SuccessResponseDTO succe;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuariosActivos()
    {
        log.debug("Listando Usuario");
        return  new ResponseEntity<>(usuarioService.listarDatos(), HttpStatus.OK);
    }
    @GetMapping("/pag")
    public ResponseEntity<Page<Usuario>> listarUsuariosPorPagina(Pageable pagUsuarios){
        log.debug("Listando Usuario por pagina");
        return new ResponseEntity<>(usuarioService.listarEntidadPorPagina(pagUsuarios), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> buscarUsuariosPorId(@PathVariable("id") Long id){
        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.FOUND_SUCCESS,
                usuarioService.buscarEntidad(id).get()
        );
        log.debug("Usuario Buscar");
        return ResponseEntity.ok(succe);
    }


    @PostMapping
    public ResponseEntity<SuccessResponseDTO> registrarUsuario(@Valid @RequestBody Usuario usuario){
        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.ENTITY_CREATED,
                usuarioService.registrarEntidad(usuario)
        );
        log.debug("Usuario Registrar");
        return ResponseEntity.ok(succe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> modificarUsuario(@Valid @RequestBody Usuario usuario,
                                                               @PathVariable("id") long id){
        usuario.setId(id);

        succe = SuccessResponseDTO.buildQuickResponse(
                ResponseMessageConstants.MODIFIED_SUCCESS,
                usuarioService.modificarEntidad(usuario)
        );
        log.debug("Usuario Modificar");
        return ResponseEntity.ok(succe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> eliminarUsuario(@PathVariable("id") Long id){
        succe = SuccessResponseDTO.buildQuickResponse(
                HttpStatus.OK,
                ResponseMessageConstants.DELETED_SUCCESS,
                usuarioService.eliminarEntidad(id)
        );
        log.debug("Usuario Eliminar");
        return new ResponseEntity<>(succe, HttpStatus.valueOf(succe.getHttpStatus()));
    }

}
