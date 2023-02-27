package com.g4.dev.esportlancentersw.reportes.service;

import com.g4.dev.esportlancentersw.exception.common.NotFoundException;
import com.g4.dev.esportlancentersw.reportes.util.ReportesConstants;
import com.g4.dev.esportlancentersw.reportes.util.ReportesUtils;
import com.g4.dev.esportlancentersw.service.IServices.IPedidoCompraService;
import com.g4.dev.esportlancentersw.util.ConexionJDBC;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.g4.dev.esportlancentersw.reportes.util.ReportesConstants.*;
import static com.g4.dev.esportlancentersw.reportes.util.ReportesConstants.JASPER_EXTENSION;


@Service
public class ReporteCompraService implements IReporteService {
    private Connection conn;
    private Map<String, Object> parameters;

    @Autowired
    IPedidoCompraService pedidoCompraService;
    @Autowired
    private ResourceLoader resourceLoader;

    public ReporteCompraService() throws SQLException {
        this.conn = ConexionJDBC.getConx();
        this.parameters = new HashMap<>();
    }


    public ByteArrayResource exportToByteArrayResource(Object idDato) {
        verifyPedidoCompraExists((Long) idDato);

        ByteArrayResource streamResource = null;

        try {
            fillMapParameterWithEntity(idDato, this.parameters);


            ClassPathResource resource = new ClassPathResource(
                    JASPER_FOLDER + File.separator + JASPER_REPORT_NAME_PEDIDO_COMPRA + JASPER_EXTENSION);

            InputStream inputStream = resource.getInputStream();
            JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, conn);

            byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
            streamResource = new ByteArrayResource(bytes);


        } catch (JRException | IOException e) {
            e.printStackTrace();
        }
        return streamResource;
    }


    @Override
    public String exportarAPdfFile(Object idDato) {

        verifyPedidoCompraExists((Long) idDato);

        String rutaPdf = "";

        try {
            String nombreDelPdf = "Reporte" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";

            String rutaFinalPdf = RESOURCES_DIR + nombreDelPdf;

            fillMapParameterWithEntity(idDato, this.parameters);

            try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {

                ClassPathResource resource = new ClassPathResource(
                        JASPER_FOLDER + File.separator + JASPER_REPORT_NAME_PEDIDO_COMPRA + JASPER_EXTENSION);

                InputStream inputStream = resource.getInputStream();
                JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, conn);

                JasperExportManager.exportReportToPdfFile(jasperPrint, rutaFinalPdf);

            }

            rutaPdf = rutaFinalPdf;
        } catch (JRException | IOException e) {
            e.printStackTrace();
        }
        return rutaPdf;
    }

    @Override
    public void fillMapParameterWithEntity(Object idPedido, Map<String, Object> parameters) {
        parameters.put(ReportesConstants.PARAM_LOGO, ReportesUtils.getBufferFromResourceImage(resourceLoader));
        parameters.put(PARAM_ID_PEDIDO_COMPRA, ((Long) idPedido).intValue());
    }

    void verifyPedidoCompraExists(long id) {
        if (!pedidoCompraService.existsByIdPedido(id)) {
            throw new NotFoundException("Pedido de compras no existente");
        }
    }
}
