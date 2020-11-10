package com.example.tictactoe.model.response;

// Classe base para as respostas
public class Response {
	public static final String ERROR = "erro";
	public static final String OK = "ok";
	private String status;
	private String message;
	
	public Response(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
