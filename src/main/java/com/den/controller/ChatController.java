package com.den.controller;

import com.den.encoder.ChatMessageDecoder;
import com.den.encoder.ChatMessageEncoder;
import com.den.exception.ApplicationException;
import com.den.model.wrapper.ChatMessage;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Denis on 19.06.2015.
 */

@ServerEndpoint(value = "/message/{room}", encoders = ChatMessageEncoder.class, decoders = ChatMessageDecoder.class)
@Component
public class ChatController {


    private static final Logger LOG = Logger.getLogger(ChatController.class.getName());

    @OnMessage
    public void onMessage(final Session session, final ChatMessage message) throws IOException, EncodeException {
        Long room = (Long) session.getUserProperties().get("room");

        for (Session s : session.getOpenSessions()) {
            if (s.isOpen()
                    && room.equals(s.getUserProperties().get("room"))) {

                s.getBasicRemote().sendObject(message);
            }
        }
        throw new ApplicationException();
    }

    @OnOpen
    public void onOpen(Session peer, @PathParam("room") final Long room) throws IOException, EncodeException {
        peer.setMaxIdleTimeout(1000*60*60);
        LOG.info("Connection opened ...");
        peer.getUserProperties().put("room", room);

    }

    @OnClose
    public void onClose(Session peer,CloseReason reason) {
        System.out.println(reason);
        LOG.info("Connection closed ...");
    }

}