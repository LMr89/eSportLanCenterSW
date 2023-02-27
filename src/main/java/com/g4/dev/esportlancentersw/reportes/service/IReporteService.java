package com.g4.dev.esportlancentersw.reportes.service;

import com.g4.dev.esportlancentersw.model.Venta;

import java.util.Map;

public interface IReporteService {
    String exportarAPdfFile(Object idDato);
    void fillMapParameterWithEntity(Object ve, Map<String , Object> parameters);
}
