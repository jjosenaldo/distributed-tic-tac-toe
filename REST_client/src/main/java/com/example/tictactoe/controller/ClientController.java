package com.example.tictactoe.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.tictactoe.model.GameInfo;
import com.example.tictactoe.model.GameStatus;
import com.example.tictactoe.model.exception.ErrorException;
import com.example.tictactoe.model.response.InfoResponse;
import com.example.tictactoe.model.response.PlayResponse;
import com.example.tictactoe.model.response.RegistrationResponse;
import com.example.tictactoe.model.response.StatusResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientController implements IClientController {
	private HttpConnection connection;
	
	public ClientController(String host, int port) {
		connection = new HttpConnection(host, port);
	}
	
	public ClientController() {
		connection = new HttpConnection("localhost", 8080);
	}
	
	@Override
	public GameInfo info(String token) throws ErrorException {
		// Send request
		Map<String, String> params = new HashMap<>();
		params.put("token", token);
		String responseJSON = connection.getRequest("/info", params);
		
		// Threat response
		ObjectMapper mapper = new ObjectMapper();
		try {
			InfoResponse response = mapper.readValue(responseJSON, InfoResponse.class);
			if(response.getStatus().equals("ok")) {
				return response.getContent();
			}
			else
				throw new ErrorException(response.getMessage());
		} catch (JsonProcessingException e) {
			throw new ErrorException(e.getMessage());
		}
	}

	@Override
	public GameStatus status(String token) throws ErrorException {
		// Send request
		Map<String, String> params = new HashMap<>();
		params.put("token", token);
		String responseJSON = connection.getRequest("/status", params);
		
		// Threat response
		ObjectMapper mapper = new ObjectMapper();
		try {
			StatusResponse response = mapper.readValue(responseJSON, StatusResponse.class);
			if(response.getStatus().equals("ok")) {
				return response.getContent();
			}
			else
				throw new ErrorException(response.getMessage());
		} catch (JsonProcessingException e) {
			throw new ErrorException(e.getMessage());
		}
	}

	@Override
	public void play(int row, int col, String token) throws ErrorException {
		// Send request
		Map<String, String> params = new HashMap<>();
		params.put("row", Integer.toString(row));
		params.put("column", Integer.toString(col));
		params.put("token", token);
		String responseJSON = connection.postRequest("/play", params);
		
		// Threat response
		ObjectMapper mapper = new ObjectMapper();
		try {
			PlayResponse response = mapper.readValue(responseJSON, PlayResponse.class);
			if(!response.getStatus().equals("ok"))
				throw new ErrorException(response.getMessage());
		} catch (JsonProcessingException e) {
			throw new ErrorException(e.getMessage());
		}
	}

	@Override
	public String register(String name) throws ErrorException {
		// Send request 
		Map<String, String> params = new HashMap<>();
		params.put("name", name);
		String responseJSON = connection.postRequest("/register", params);
		
		// Threat response
		ObjectMapper mapper = new ObjectMapper();
		try {
			RegistrationResponse response = mapper.readValue(responseJSON, RegistrationResponse.class);
			if(response.getStatus().equals("ok")) {
				return response.getContent().getToken();
			}
			else
				throw new ErrorException(response.getMessage());
		} catch (JsonProcessingException e) {
			throw new ErrorException(e.getMessage() + "\n\n\nClass error: " + e.getClass().getCanonicalName());
		}
	}
}
