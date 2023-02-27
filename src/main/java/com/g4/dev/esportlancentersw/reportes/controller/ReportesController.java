package com.g4.dev.esportlancentersw.reportes.controller;


import com.g4.dev.esportlancentersw.reportes.service.ReporteCompraService;
import com.g4.dev.esportlancentersw.reportes.service.TiicketVentaService;
import com.g4.dev.esportlancentersw.reportes.util.ReportesResponseEntity;
import com.g4.dev.esportlancentersw.reportes.util.ReportesUtils;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

@RestController
@RequestMapping("/reportes")
public class ReportesController {
    @Autowired private TiicketVentaService service;
    @Autowired private ReporteCompraService reporteCompraService;

    @GetMapping("/ticketVenta")
    public ResponseEntity<Resource> descargarPdf(@RequestParam("venta") long id)
            throws IOException{

        //service.exportar(id);
        String ruta = service.exportarAPdfFile(id);
        ByteArrayResource resource = ReportesUtils.getBytesFromPdfFile(ruta);
        ReportesUtils.eliminarPdfFile(ruta);
        return ReportesResponseEntity.sendPdfIntoResponse(resource, ruta);
    }

    @GetMapping("/pedidoCompra")
    public ResponseEntity<Resource> descargarReporteComprasPdf(@RequestParam("pedidoCompra") long id)
            throws IOException{
        ByteArrayResource resource =reporteCompraService.exportToByteArrayResource(id);
        return ReportesResponseEntity.sendPdfIntoResponse(resource);
    }






}
