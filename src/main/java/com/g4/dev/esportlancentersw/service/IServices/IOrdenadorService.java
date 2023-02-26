package com.g4.dev.esportlancentersw.service.IServices;

import com.g4.dev.esportlancentersw.model.Ordenador;
import com.g4.dev.esportlancentersw.service.IMainService;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface IOrdenadorService extends IMainService<Ordenador> {

    void ponerEnMantenimientoOrdenador(long id);
    void levantarMantenimiento(long id);

    List<Ordenador> ordenadoresEnMantenimiento();

    boolean existsById(long id);

}
