package com.g4.dev.esportlancentersw.service.IServices;

import com.g4.dev.esportlancentersw.model.Ordenador;
import com.g4.dev.esportlancentersw.service.IMainService;
import org.aspectj.weaver.ast.Or;

import java.util.List;
import java.util.Optional;

public interface IOrdenadorService extends IMainService<Ordenador> {

    void ponerEnMantenimientoOrdenador(long id);
    void levantarMantenimiento(long id);

    List<Ordenador> ordenadoresEnMantenimiento();
    List<Ordenador> ordenadoresEnLinea();
    boolean existsById(long id);

    Optional<String> findDireccionMacFromId(Long id);

}
