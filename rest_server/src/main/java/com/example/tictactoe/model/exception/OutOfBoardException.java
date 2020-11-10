package com.example.tictactoe.model.exception;

public class OutOfBoardException extends Exception {
	private static final long serialVersionUID = 5593991773076077612L;
	public OutOfBoardException() {
		super("Os índices fornecidos estão fora dos limites do campo");
	}
}
