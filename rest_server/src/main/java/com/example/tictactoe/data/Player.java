package com.example.tictactoe.data;

public class Player {
	private String token;
	private String name;
	private String label;
	
	public Player(String token, String name, String label) {
		super();
		this.token = token;
		this.name = name;
		if(label.equals("X") || label.equals("O"))
			this.label = label;
		else
			this.label = null;
	}

	public String getToken() {
		return token;
	}

	public void setId(String token) {
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
		if(label.equals("X") || label.equals("O"))
			this.label = label;
		else
			this.label = null;
	}
}
