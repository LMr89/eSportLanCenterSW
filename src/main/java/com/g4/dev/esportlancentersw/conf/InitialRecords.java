package com.g4.dev.esportlancentersw.conf;

import com.g4.dev.esportlancentersw.security.enums.RolNombre;
import com.g4.dev.esportlancentersw.security.model.Rol;
import com.g4.dev.esportlancentersw.security.model.Usuario;
import com.g4.dev.esportlancentersw.security.repository.RolRepository;
import com.g4.dev.esportlancentersw.security.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;


/**
 * Esta clase hara registros iniciales para que el
 * servicio web funcione correctamente una vez desplegado en
 * un servidor
 * @author Luis DEV
 * @since 1.0
 */
@Slf4j
@Component
public class InitialRecords implements CommandLineRunner {

    @Autowired private RolRepository rolRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        if (rolRepository.findAll().isEmpty()){
            registerNewRoles();
            registerNewUsuario();
        }
    }

    /**
     * Método encargado de registrar los primeros roles
     * para el inicio del servicio spring
     * @author Luis DEV
     * @since 1.0
     */
    void registerNewRoles(){
        rolRepository.save(new Rol(1, RolNombre.ROL_USER));
        rolRepository.save(new Rol(2, RolNombre.ROL_ADMIN));

        log.info("Se han creado los roles iniciales");
    }

    /**
     * Método encargado de registrar al primer usuario admin
     * para el inicio del servicio spring
     * @author Luis DEV
     * @since 1.0
     */
    void registerNewUsuario(){
        usuarioRepository.save(
                Usuario.builder()
                        .nombre("Chavoncio Gonzales")
                        .nomUsuario("admin")
                        .password("adminG4")
                        .correo("scoobyDoo@gmail.com")
                        .nombre("Ramon")
                        .apellido("Gutierrez")
                        .dni("12345678")
                        .telefono("123456789")
                        .roles(Collections.singleton(Rol.builder().id(1).build()))
                        .build()
        );
        log.info("Se ha creado el primero usuario admin con contraseña adminG4");
    }
}
