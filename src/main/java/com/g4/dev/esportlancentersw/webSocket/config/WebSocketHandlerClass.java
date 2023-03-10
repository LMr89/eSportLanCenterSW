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


    private final Map<String, WebSocketSession> sesionesOrdenadores =
            new ConcurrentHashMap<>();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String ordenadorId = Objects.requireNonNull(session.getUri())
                .getQuery()
                .replace("clientId=","");
        webSocketLogger.info("Conexion establecida del cliente: "  + ordenadorId);
        sesionesOrdenadores.put(ordenadorId, session);

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
        String ordenadorId = Objects.requireNonNull(session.getUri())
                .getQuery()
                .replace("clientId=","");

        sesionesOrdenadores.remove(ordenadorId);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendMessageToOrdenador(String clientId, String message) throws IOException {
        WebSocketSession session = sesionesOrdenadores.get(clientId);
        if (session != null) {
            session.sendMessage(new TextMessage(message));
        }
    }
}
