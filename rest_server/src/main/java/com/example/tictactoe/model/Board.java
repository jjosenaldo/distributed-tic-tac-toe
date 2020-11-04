package com.example.tictactoe.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private String[][] board;
	
	public Board() {
		board = new String[3][3];
		this.clear();
	}
	
	public void clear() {
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				board[i][j] = " ";
	}
	
	public String getCell(int i, int j) {
		return board[i][j];
	}
	
	public void setCell(int i, int j, String value) {
		board[i][j] = value;
	}
	
	public List<List<String>> toJSONMatrix() {
		List<List<String>> board = new ArrayList<>();

		for(int i = 0; i < 3; i++) {
			List<String> tmp = new ArrayList<String>();
			for(int j = 0; j < 3; j++)
				tmp.add(this.board[i][j]);
			board.add(tmp);
		}		
		return board;
	}
}
