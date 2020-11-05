package com.example.tictactoe.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.http.HttpStatus;

// Classe base para as respostas
public class Response {
	public static final String ERROR = "erro";
	public static final String OK = "ok";
	private String status;
	private String message;

	@JsonIgnore
	private final HttpStatus httpStatus;

	public Response(String status, String message, HttpStatus httpStatus) {
		this.status = status;
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
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
