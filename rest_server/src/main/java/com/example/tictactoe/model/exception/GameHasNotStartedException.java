package com.example.tictactoe.model.exception;

public class GameHasNotStartedException extends Exception {
	private static final long serialVersionUID = 2046261155692467451L;
	public GameHasNotStartedException() {
		super("O jogo ainda não começou.");
	}
}
