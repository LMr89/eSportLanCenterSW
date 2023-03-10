package com.g4.dev.esportlancentersw.webSocket.controller;

import com.g4.dev.esportlancentersw.webSocket.config.WebSocketHandlerClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/action-ordenador")
public class WebSocketController {

    @Autowired
    private WebSocketHandlerClass socketHandlerClass;


    @PostMapping("/start-conversation")
    public void startConversation() throws IOException {
        // start sending messages to the specified client
        socketHandlerClass.sendMessageToOrdenador("1", "Hello from the server.");
        log.info("postMapping initialized");
    }

}
