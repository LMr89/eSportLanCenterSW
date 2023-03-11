package com.g4.dev.esportlancentersw.webSocket.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.g4.dev.esportlancentersw.DTO.response.alquiler.AcctionDTO;
import com.g4.dev.esportlancentersw.webSocket.SessionsUtil;
import com.g4.dev.esportlancentersw.webSocket.exception.NotOrdenadorConnected;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;

@Service
@Data
public class WebSocketAlquilerService {
    @Autowired private ObjectMapper objectMapper;

    private Map<String , WebSocketSession > sessionMap;

    /**
     * Método encargado de enviar los mensajes acciones al cliente .net
     * @param ordenadorMacAdd string de la direccion MAC
     * @param   acctionDTO clase de transporte de los datos al cliente
     * @return isSent booleano que indica si el envio fue exitoso
     * @author Luis DEV
     * @since 1.0
     */
    public  boolean sendActionToClient(String ordenadorMacAdd, AcctionDTO acctionDTO) throws IOException {
        boolean isSent =  false;
        getSessionFromMac(ordenadorMacAdd)
                .sendMessage(new TextMessage(convertDtoToJsonString(acctionDTO)));
        return  isSent;
    }


    /**
     * Método encargado de encontrar la session apartir de la direccion mac almacenadas en memoria
     * @param ordenadorMac direccion mac del ordenador
     * @return sess WebSocketSession guardada en el concurrentHashMap
     * @author Luis DEV
     * @since 1.0
     */
    WebSocketSession getSessionFromMac(String ordenadorMac){
        WebSocketSession sess = SessionsUtil.sessionMap
                .get(ordenadorMac);
        if (sess == null){
            throw  new NotOrdenadorConnected();
        }

        return  sess;
    }


    /**
     * Método encargada de convertir el objeto  AcctionDTO dto  a un json string
     * @param dto clase dto para enviar acciones al cliente .net
     * @return string la clase convertida en string para el transporte
     * @author Luis DEV
     * @since 1.0
     */

    private String convertDtoToJsonString(AcctionDTO dto) throws JsonProcessingException {
        return  objectMapper.writeValueAsString(dto);
    }




}
