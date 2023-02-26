package com.g4.dev.esportlancentersw.service.IServices;

import com.g4.dev.esportlancentersw.model.Cliente;
import com.g4.dev.esportlancentersw.service.IMainService;

public interface IClienteService extends IMainService<Cliente> {
    boolean existsById(long id);
}
