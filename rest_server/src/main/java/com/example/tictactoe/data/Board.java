package com.example.tictactoe.data;

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
		if(i < 0 || i > 3)
			return null;
		if(j < 0 || j > 3)
			return null;
		return board[i][j];
	}
	
	public void setCell(int i, int j, String value) {
		if(i < 0 || i > 3)
			return;
		if(j < 0 || j > 3)
			return;
		board[i][j] = value;
	}
}
