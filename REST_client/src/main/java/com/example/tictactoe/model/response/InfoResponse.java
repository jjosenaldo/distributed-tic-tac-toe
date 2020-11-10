package com.example.tictactoe.model.response;

import com.example.tictactoe.model.GameInfo;

public class InfoResponse extends Response {
	private GameInfo content;
	
	public InfoResponse() {
		super(null, null);
		content = null;
	}
	
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
}
