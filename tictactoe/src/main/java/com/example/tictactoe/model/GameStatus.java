package com.example.tictactoe.model;

import java.util.List;

public class GameStatus {
	public static class Status {
		public static final String NOT_STARTED = "não começou";
		public static final String RUNNING = "executando";
		public static final String DRAW = "empate";
		public static final String WIN = "venceu";
		public static final String LOSE = "perdeu";
	}
	
	private String status;
    private Boolean yourTurn;
    private List<List<String>> board;
    private List<List<Integer>> winCoordinates;
    
    public GameStatus() {
    	this.status = null;
    	this.yourTurn = null;
    	this.board = null;
    	this.winCoordinates = null;
	}
    
    public GameStatus(String status, Boolean yourTurn, 
    		List<List<String>> board, List<List<Integer>> winCoordinates) {
        this.status = status;
        this.yourTurn = yourTurn;
        this.board = board;
        this.winCoordinates = winCoordinates;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getYourTurn() {
		return yourTurn;
	}

	public void setYourTurn(Boolean isYourTurn) {
		this.yourTurn = isYourTurn;
	}

	public List<List<String>> getBoard() {
		return board;
	}

	public void setBoard(List<List<String>> board) {
		this.board = board;
	}

	public List<List<Integer>> getWinCoordinates() {
		return winCoordinates;
	}

	public void setWinCoordinates(List<List<Integer>> winCoordinates) {
		this.winCoordinates = winCoordinates;
	}
}
