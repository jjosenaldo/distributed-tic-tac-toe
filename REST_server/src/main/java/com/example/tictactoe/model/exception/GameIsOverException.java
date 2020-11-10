package com.example.tictactoe.model.exception;

public class GameIsOverException extends Exception {
	private static final long serialVersionUID = 6301879687432068458L;
	public GameIsOverException() {
		super("O jogo já acabou.");
	}
}
