package com.g4.dev.esportlancentersw.reportes.service;

import com.g4.dev.esportlancentersw.model.Venta;
import com.g4.dev.esportlancentersw.reportes.util.ReportesConstants;
import com.g4.dev.esportlancentersw.service.IServices.IVentaService;
import com.g4.dev.esportlancentersw.util.ConexionJDBC;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.g4.dev.esportlancentersw.reportes.util.ReportesConstants.*;

@Service
public class TiicketVentaService {
    private Connection conn ;
    private Map<String , Object> parameters;
    private Venta currentVenta;

    @Autowired private IVentaService ventaService;

    public TiicketVentaService() throws SQLException {
        this.conn = ConexionJDBC.getConx();
        this.parameters = new HashMap<>();
    }

    public ByteArrayOutputStream exportar(long idVenta)
            throws IOException, JRException {

        /* Agregar la imagene al reporte */
        currentVenta  = searchVentabd(idVenta);
        fillMapParameterWithEntity(currentVenta,this.parameters);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ClassPathResource resource = new ClassPathResource(JASPER_FOLDER + File.separator + JASPER_REPORT_NAME + JASPER_EXTENSION);

        InputStream inputStream = resource.getInputStream();
        JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, this.parameters, this.conn);

        /* Convertirlo a PDF */
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        return stream;

    }

    public ByteArrayInputStream exportarToInputStream(long idVenta)
            throws IOException, JRException {

        /* Agregar la imagene al reporte */
        currentVenta  = searchVentabd(idVenta);
        fillMapParameterWithEntity(currentVenta,this.parameters);


        byte[] bs = exportar (idVenta).toByteArray();
        return new ByteArrayInputStream(bs);

    }

    public String exportarAPdfFile(long idVenta) throws IOException {
        String rutaPdf = "";

        try {
            String rutaCarpeta = "src/main/resources/reportes/";
            String nombreDelPdf = "ticket" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";

            String rutaFinalPdf = rutaCarpeta + nombreDelPdf;
            /* Agregar la imagene al reporte */
            currentVenta  = searchVentabd(idVenta);
            fillMapParameterWithEntity(currentVenta,this.parameters);

            try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {

                ClassPathResource resource = new ClassPathResource(
                        JASPER_FOLDER + File.separator + JASPER_REPORT_NAME + JASPER_EXTENSION);

                InputStream inputStream = resource.getInputStream();
                JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, conn);

                JasperExportManager.exportReportToPdfFile(jasperPrint, rutaFinalPdf);

            }

            rutaPdf = rutaFinalPdf;
        } catch (JRException e) {
            e.printStackTrace();
        }
        return rutaPdf;
    }

    public void eliminarPdfFile(String rutaPdf) {
        try {
            Path path = Paths.get(rutaPdf);
            Files.delete(path);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    Venta searchVentabd(long id){
        return ventaService.buscarEntidad(id).get();
    }

    void fillMapParameterWithEntity(Venta ve, Map<String , Object> parameters){
        parameters.put(ReportesConstants.PARAM_LOGO, JASPER_FOLDER + File.separator + "imagenes" + File.separator + "gusesprort.jpg");
        parameters.put(PARAM_ID_VENTA, ve.getIdVenta().intValue());
        parameters.put(PARAM_TICKET_NUM, "T00000"+ve.getIdVenta());
        parameters.put(PARAM_CLIENTE_NUM, ve.getIdCliente().getNombre());
        parameters.put(PARAM_SUBTOTAL, ve.getTotal() - ve.getIgv());
        parameters.put(PARAM_IGV, ve.getIgv());
        parameters.put(PARAM_TOTAL, ve.getTotal());
        parameters.put(PARAM_VENDEDOR, ve.getIdUsuario().getNomUsuario());




    }


}
