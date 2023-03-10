package com.g4.dev.esportlancentersw.webSocket.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSockConf
        implements org.springframework.web.socket.config.annotation.WebSocketConfigurer {


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketHandlerClass(), "/ws")
                .setAllowedOrigins("*");

    }



}
