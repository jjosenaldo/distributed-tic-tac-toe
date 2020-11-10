package com.example.tictactoe.model.exception;

public class TokenDoesNotExistException  extends Exception {
	private static final long serialVersionUID = -779428043501364164L;
	public TokenDoesNotExistException() {
		super("O token fornecido não pertence a nenhum jogador.");
	}
}
