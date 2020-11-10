package com.example.tictactoe.model.response;

import com.example.tictactoe.model.GameInfo;

import org.springframework.http.HttpStatus;

public class InfoResponse extends Response {
	private GameInfo content;

	public InfoResponse(String status, String message, GameInfo content, HttpStatus httpStatus) {
		super(status, message, httpStatus);
		this.content = content;
	}

	public GameInfo getContent() {
		return content;
	}

	public void setContent(GameInfo content) {
		this.content = content;
	}

	public static InfoResponse tokenIsInvalid() {
		return new InfoResponse(Response.ERROR, "O token fornecido é inválido.", null, HttpStatus.UNAUTHORIZED);
	}

	public static InfoResponse gameHasNotStartedYet() {
		return new InfoResponse(Response.ERROR, "O jogo ainda não começou.", null, HttpStatus.FORBIDDEN);
	}

	public static InfoResponse ok(GameInfo content) {
		return new InfoResponse(Response.OK, "Sucesso.", content, HttpStatus.OK);
	}
}
