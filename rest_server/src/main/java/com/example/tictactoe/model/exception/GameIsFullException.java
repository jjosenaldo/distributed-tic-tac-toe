package com.example.tictactoe.model.exception;

public class GameIsFullException extends Exception {
	private static final long serialVersionUID = -4421077053401896109L;
	public GameIsFullException() {
		super("Não há vagas disponíveis.");
	}
}
