package com.g4.dev.esportlancentersw.security.service;


import com.g4.dev.esportlancentersw.exception.common.DniRepeatedException;
import com.g4.dev.esportlancentersw.exception.common.NotFoundException;
import com.g4.dev.esportlancentersw.exception.usuario.UsuarioNomUsuarioRepeatedException;
import com.g4.dev.esportlancentersw.security.enums.RolNombre;
import com.g4.dev.esportlancentersw.security.model.Rol;
import com.g4.dev.esportlancentersw.security.model.Usuario;
import com.g4.dev.esportlancentersw.security.repository.UsuarioRepository;
import com.g4.dev.esportlancentersw.service.IServices.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UsuarioService implements IUsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    RolService rolService;

    public Optional<Usuario> getByNomUsuario(String nomUsuario) {
        return usuarioRepository.findByNomUsuario(nomUsuario);
    }


    @Override
    public List<Usuario> listarDatos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> buscarEntidad(Long id) {
        Optional<Usuario> usuarioFound = usuarioRepository.findById(id);
        if (usuarioFound.isEmpty()) {
            throw new NotFoundException("Usuario NO ENCONTRADO");
        }

        return usuarioFound;
    }

    @Override
    public Page<Usuario> listarEntidadPorPagina(Pageable p) {
        return usuarioRepository.findAll(p);
    }

    @Override
    public Usuario registrarEntidad(Usuario entidad) {
        validatedUsuarioInput(entidad);
        entidad.setPassword(encoder.encode(entidad.getPassword()));

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROL_USER).get());
        if (entidad.getRoles().contains("admin")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROL_ADMIN).get());

        }
        entidad.setRoles(roles);
        return usuarioRepository.save(entidad);
    }

    @Override
    public Usuario modificarEntidad(Usuario entidad) {
        Optional<Usuario> usuarioFound = buscarEntidad(entidad.getId());
        Optional<Usuario> usuarioFoundByNomUsuario = getByNomUsuario(entidad.getNomUsuario());


        //Si el dni del usuario encontrado por el NOMBRE DE USUARIO no es igual
        //al usuario encontrado por id eso quiere decir que es una actualizacion
        // el cual no genererara error debido a que existe otro usuario con el mismo nomUsuario

        if (usuarioFound.isPresent() && usuarioFoundByNomUsuario.isPresent()){
            if (!usuarioFound.get().getDni().equals(usuarioFoundByNomUsuario.get().getDni())) {
                throw new UsuarioNomUsuarioRepeatedException();
            }
            entidad.setPassword(encoder.encode(entidad.getPassword()));
            Set<Rol> roles = new HashSet<>();
            roles.add(rolService.getByRolNombre(RolNombre.ROL_USER).get());

            Optional<Rol> rolFounded = entidad.getRoles()
                    .stream()
                    .filter(rol -> rol.getRolNombre().equals(RolNombre.ROL_ADMIN))
                    .findAny();

            if (rolFounded.isPresent()) {
                roles.add(rolService.getByRolNombre(RolNombre.ROL_ADMIN).get());

            }
            entidad.setRoles(roles);
            usuarioRepository.save(entidad);



        }


        return entidad;
    }

    @Override
    public boolean eliminarEntidad(Long id) {
        Optional<Usuario> userFound = buscarEntidad(id);
        userFound.ifPresent(usuario -> usuarioRepository.delete(usuario));
        return true;
    }

    private void validatedUsuarioInput(Usuario usu) {

        if (usuarioRepository.existsByDni(usu.getDni())) {
            throw new DniRepeatedException();
        }
        if (usuarioRepository.existsByNomUsuario(usu.getNomUsuario())) {
            throw new UsuarioNomUsuarioRepeatedException();
        }
    }

    @Override
    public boolean existsById(long id) {
        return usuarioRepository.existsById(id);
    }
}
