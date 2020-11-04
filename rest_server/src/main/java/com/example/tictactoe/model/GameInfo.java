package com.example.tictactoe.model;

public class GameInfo {
    private final String opponentName;
    private final String opponentLabel;
    private final String yourName;
    private final String yourLabel;
    private final String initialPlayerName;
    
    public GameInfo(String opponentName, String opponentLabel, 
    		String yourName, String yourLabel, String initialPlayerName) {
        this.opponentName = opponentName;
        this.opponentLabel = opponentLabel;
        this.yourName = yourName;
        this.yourLabel = yourLabel;
        this.initialPlayerName = initialPlayerName;
    }

    public String getOpponentLabel() {
        return opponentLabel;
    }

    public String getInitialPlayerName() {
        return initialPlayerName;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public String getYourLabel() {
        return yourLabel;
    }

    public String getYourName() {
        return yourName;
    }
}
