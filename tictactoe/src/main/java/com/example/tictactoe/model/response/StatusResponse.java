package com.example.tictactoe.model.response;

import com.example.tictactoe.model.GameStatus;

public class StatusResponse extends Response {
	private GameStatus content;
	
	public StatusResponse() {
		super(null, null);
		content = null;
	}
	
	public StatusResponse(String status, String message, GameStatus content) {
		super(status, message);
		this.content = content;
	}
	
	public GameStatus getContent() {
		return content;
	}
	
	public void setContent(GameStatus content) {
		this.content = content;
	}
}
