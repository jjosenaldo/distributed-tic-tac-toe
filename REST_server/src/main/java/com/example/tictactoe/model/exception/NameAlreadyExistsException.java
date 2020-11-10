package com.example.tictactoe.model.exception;

public class NameAlreadyExistsException extends Exception {
	private static final long serialVersionUID = -1958369313478583499L;
	public NameAlreadyExistsException() {
		super("O nome informado já está em uso.");
	}
}
