package com.den.encoder;

import com.den.exception.ApplicationException;
import com.den.model.wrapper.ChatMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {
	private ObjectMapper mapper = new ObjectMapper();
	@Override
	public void init(final EndpointConfig config) {
	}
 
	@Override
	public void destroy() {
	}
 
	@Override
	public String encode(final ChatMessage chatMessage) throws EncodeException {
		try {
			return mapper.writeValueAsString(chatMessage);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		throw new ApplicationException("ERROR PARSE CHAT MESSAGE");
	}
}