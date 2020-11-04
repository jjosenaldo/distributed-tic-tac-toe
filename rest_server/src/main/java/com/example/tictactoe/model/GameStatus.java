package com.example.tictactoe.model;

import java.util.List;

public class GameStatus {
	public static class Status {
		public static final String RUNNING = "executando";
		public static final String DRAW = "empate";
		public static final String WIN = "venceu";
		public static final String LOSE = "perdeu";
	}
	
	private final String status;
    private final Boolean isYourTurn;
    private final List<List<String>> board;
    private final List<List<Integer>> winCoordinates;
    
    public GameStatus(String status, Boolean isYourTurn, 
    		List<List<String>> board, List<List<Integer>> winCoordinates) {
        this.status = status;
        this.isYourTurn = isYourTurn;
        this.board = board;
        this.winCoordinates = winCoordinates;
    }
    
    public String getStatus() {
        return status;
    }
    
    public Boolean isYourTurn() {
        return isYourTurn;
    }
    
    public List<List<String>> getBoard() {
        return board;
    }

    public List<List<Integer>> getWinCoordinates() {
		return winCoordinates;
	}
}
