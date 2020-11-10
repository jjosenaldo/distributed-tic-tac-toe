package com.example.tictactoe.model;

public class GameInfo {
	private String opponentName;
    private String opponentLabel;
    private String yourName;
    private String yourLabel;
    private String initialPlayerName;
    
	public GameInfo() {
		this.opponentName = null;
	    this.opponentLabel = null;
	    this.yourName = null;
	    this.yourLabel = null;
	    this.initialPlayerName = null;
	}
	
	public GameInfo(String opponentName, String opponentLabel, String yourName, String yourLabel,
			String initialPlayerName) {
		this.opponentName = opponentName;
		this.opponentLabel = opponentLabel;
		this.yourName = yourName;
		this.yourLabel = yourLabel;
		this.initialPlayerName = initialPlayerName;
	}

	public String getOpponentName() {
		return opponentName;
	}

	public void setOpponentName(String opponentName) {
		this.opponentName = opponentName;
	}

	public String getOpponentLabel() {
		return opponentLabel;
	}

	public void setOpponentLabel(String opponentLabel) {
		this.opponentLabel = opponentLabel;
	}

	public String getYourName() {
		return yourName;
	}

	public void setYourName(String yourName) {
		this.yourName = yourName;
	}

	public String getYourLabel() {
		return yourLabel;
	}

	public void setYourLabel(String yourLabel) {
		this.yourLabel = yourLabel;
	}

	public String getInitialPlayerName() {
		return initialPlayerName;
	}

	public void setInitialPlayerName(String initialPlayerName) {
		this.initialPlayerName = initialPlayerName;
	}
}
