package com.example.tictactoe.model;

public class GameStatus {
    public GameStatus(String status, String opponentName, String opponentLabel, String yourName, String yourLabel,
            boolean isYourTurn, String[][] board) {
        this.status = status;
        this.opponentName = opponentName;
        this.opponentLabel = opponentLabel;
        this.yourName = yourName;
        this.yourLabel = yourLabel;
        this.isYourTurn = isYourTurn;
        this.board = board;
    }

    public String[][] getBoard() {
        return board;
    }

    private final String status;
    private final String opponentName;
    private final String opponentLabel;
    private final String yourName;
    private final String yourLabel;
    private final boolean isYourTurn;
    private final String[][] board;

    public String getStatus() {
        return status;
    }

    public boolean isYourTurn() {
        return isYourTurn;
    }

    public String getYourLabel() {
        return yourLabel;
    }

    public String getYourName() {
        return yourName;
    }

    public String getOpponentLabel() {
        return opponentLabel;
    }

    public String getOpponentName() {
        return opponentName;
    }

}
