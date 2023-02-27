package com.g4.dev.esportlancentersw.reportes.util;

import com.g4.dev.esportlancentersw.util.UtilDate;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.function.ServerRequest;

import java.nio.file.Paths;

public class ReportesResponseEntity {
    public  static ResponseEntity<Resource> sendPdfIntoResponse(ByteArrayResource resource, String filePath){
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+ Paths.get(filePath).getFileName()+"\".pdf")
                .contentLength(resource.contentLength())
                .contentType( MediaType.APPLICATION_PDF)
                .body(resource);
    }

    public  static ResponseEntity<Resource> sendPdfIntoResponse(ByteArrayResource resource){
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"Reporte_"+ UtilDate.getDateToStringForPdf() +"\".pdf")
                .contentLength(resource.contentLength())
                .contentType( MediaType.APPLICATION_PDF)
                .body(resource);
    }


}
