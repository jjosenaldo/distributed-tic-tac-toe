package com.example.tictactoe.model.exception;

public class NotYourTurnException extends Exception {
	private static final long serialVersionUID = 9039681026901167079L;
	public NotYourTurnException() {
		super("Não é a sua vez.");
	}
}
