package com.g4.dev.esportlancentersw.reportes.controller;


import com.g4.dev.esportlancentersw.reportes.service.TiicketVentaService;
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

    @GetMapping("/ticketVenta")
    public ResponseEntity<Resource> descargarPdf(@RequestParam("venta") long id)
            throws IOException{

        //service.exportar(id);
        String ruta = service.exportarAPdfFile(id);
        byte[] data = Files.readAllBytes(Paths.get(ruta));
        ByteArrayResource resource = new ByteArrayResource(data);
        MediaType mediaType = MediaType.APPLICATION_PDF;

        service.eliminarPdfFile(ruta);
        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\""+Paths.get(ruta).getFileName()+"\".pdf")
                .contentLength(resource.contentLength())
                .contentType(mediaType)
                .body(resource);
    }






}
