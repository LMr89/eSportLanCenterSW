package com.g4.dev.esportlancentersw.webSocket.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class WebSocketHandlerClass implements WebSocketHandler {
    private  final Logger webSocketLogger = LogManager.getLogger(WebSocketHandlerClass.class);
    public static final Map<String, WebSocketSession> sesionesOrdenadores ;

    static {
        sesionesOrdenadores =
        new ConcurrentHashMap<>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        String macAddress = Objects.requireNonNull(session.getUri())
                .getQuery()
                .replace("clientMac=","");
        webSocketLogger.info("Conexion establecida del cliente: "  + macAddress);
        sesionesOrdenadores.put(macAddress, session);

        session.sendMessage(new TextMessage("Hola desde el servidor web socket spring boot"));


    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
            webSocketLogger.info("IncommingMessage: " + message.getPayload());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
            webSocketLogger.error(exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String macAddress = Objects.requireNonNull(session.getUri())
                .getQuery()
                .replace("clientMac=","");

        sesionesOrdenadores.remove(macAddress);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendMessageToOrdenador(String clienteMac, String message) throws IOException {
        WebSocketSession session = sesionesOrdenadores.get(clienteMac);
        if (session != null) {
            session.sendMessage(new TextMessage(message));
        }
    }
}
