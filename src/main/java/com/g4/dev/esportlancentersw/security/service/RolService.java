package com.g4.dev.esportlancentersw.security.service;

import com.g4.dev.esportlancentersw.security.enums.RolNombre;
import com.g4.dev.esportlancentersw.security.model.Rol;
import com.g4.dev.esportlancentersw.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return  rolRepository.findByRolNombre(rolNombre);
    }

    public void rolSave(Rol rol){
        rolRepository.save(rol);
    }


}
