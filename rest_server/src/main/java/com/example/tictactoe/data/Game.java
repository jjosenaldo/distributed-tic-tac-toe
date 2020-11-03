package com.example.tictactoe.data;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private List<Player> players;
	private Board board;
	private String currPlayerToken;
	
	public Game() {
		board = new Board();
		currPlayerToken = null;
		players = new ArrayList<>();
	}
	
	// TODO: Fazer a lógica deste método
	public void play(String token, int row, int col, String label) {
		board.setCell(row, col, label);
	}
	
	public boolean isFull() {
		return players.size() >= 2;
	}
	
	public boolean constains(String name) {
		for(Player player : players)
			if(player.getName().equals(name))
				return true;
		return false;
	}
	
	public String addPlayer(String name) {
		String token = this.generateToken();
		String label = players.isEmpty() ? "X" : "O";

		Player player = new Player(token, name, label);
		
		players.add(player);
		if(currPlayerToken == null)
			currPlayerToken = player.getToken();
		
		return token;
	}
	
	// TODO: Ver como gerar bons tokens
	public String generateToken() {
		return Integer.toString(players.size());
	}
}
