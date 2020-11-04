package com.example.tictactoe.model.response;

import com.example.tictactoe.model.GameInfo;

public class InfoResponse extends Response {
	private GameInfo content;
	public InfoResponse(String status, String message, GameInfo content) {
		super(status, message);
		this.content = content;
	}
	public GameInfo getContent() {
		return content;
	}
	public void setContent(GameInfo content) {
		this.content = content;
	}
	
	public static InfoResponse tokenIsInvalid() {
        return new InfoResponse(Response.ERROR, "O token fornecido é inválido.", null);
    }

    public static InfoResponse gameHasNotStartedYet() {
        return new InfoResponse(Response.ERROR, "O jogo ainda não começou.", null);
    }

    public static InfoResponse ok(GameInfo content) {
        return new InfoResponse(Response.OK, "Sucesso.", content);
    }
}
