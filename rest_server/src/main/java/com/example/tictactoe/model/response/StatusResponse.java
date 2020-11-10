package com.example.tictactoe.model.response;

import com.example.tictactoe.model.GameStatus;

import org.springframework.http.HttpStatus;

public class StatusResponse extends Response {
	private GameStatus content;

	public StatusResponse(String status, String message, GameStatus content, HttpStatus httpStatus) {
		super(status, message, httpStatus);
		this.content = content;
	}

	public GameStatus getContent() {
		return content;
	}

	public void setContent(GameStatus content) {
		this.content = content;
	}

	public static StatusResponse tokenIsInvalid() {
		return new StatusResponse(Response.ERROR, "O token fornecido é inválido.", null, HttpStatus.UNAUTHORIZED);
	}

	public static StatusResponse ok(GameStatus content) {
		return new StatusResponse(Response.OK, "Sucesso.", content, HttpStatus.OK);
	}
}
