package com.example.tictactoe.model;

public class GameInfo {
    private final String opponentName;
    private final String opponentLabel;
    private final String yourName;
    private final String yourLabel;

    public String getOpponentLabel() {
        return opponentLabel;
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

    public GameInfo(String opponentName, String opponentLabel, String yourName, String yourLabel) {
        this.opponentName = opponentName;
        this.opponentLabel = opponentLabel;
        this.yourName = yourName;
        this.yourLabel = yourLabel;
    }
}
