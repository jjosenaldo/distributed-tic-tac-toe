package com.example.tictactoe.model;

public class Player {
	private String token;
	private String name;
	private String label;
	
	public Player(String token, String name, String label) {
		this.token = token;
		this.name = name;	
		this.label = label;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
