package com.den.encoder;

import com.den.exception.ApplicationException;
import com.den.model.wrapper.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;

public class ChatMessageDecoder implements Decoder.Text<ChatMessage> {

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public void init(final EndpointConfig config) {
	}
 
	@Override
	public void destroy() {
	}
 
	@Override
	public ChatMessage decode(final String textMessage) throws DecodeException {
		try {
			ChatMessage chatMessage =  mapper.readValue(textMessage, ChatMessage.class);
			chatMessage.setReceived(new Date());
			return chatMessage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new ApplicationException("PARSE CHAT MESSAGE JSON ERROR");
	}
 
	@Override
	public boolean willDecode(final String s) {
		return true;
	}
}