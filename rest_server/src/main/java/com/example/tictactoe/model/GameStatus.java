package com.example.tictactoe.model;

public class GameStatus {
    public GameStatus(String status, boolean isYourTurn, String[][] board) {
        this.status = status;
        this.isYourTurn = isYourTurn;
        this.board = board;
    }

    public String[][] getBoard() {
        return board;
    }

    private final String status;
    private final boolean isYourTurn;
    private final String[][] board;

    public String getStatus() {
        return status;
    }

    public boolean isYourTurn() {
        return isYourTurn;
    }

}
