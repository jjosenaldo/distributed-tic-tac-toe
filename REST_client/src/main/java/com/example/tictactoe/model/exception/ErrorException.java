package com.example.tictactoe.model.exception;

public class ErrorException extends Exception {
	private static final long serialVersionUID = 4983002248076523911L;
	public ErrorException() {
		super();
	}
	public ErrorException(String message) {
		super(message);
	}
}
