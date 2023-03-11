package com.g4.dev.esportlancentersw.webSocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionsUtil {
    public static final  Map<String, WebSocketSession> sessionMap;

    static{
        sessionMap = new ConcurrentHashMap<>();
    }
}
