package com.example.tictactoe.model.exception;

public class CellNotAvailableException extends Exception {
    private static final long serialVersionUID = -3444950725106316137L;

    public CellNotAvailableException() {
        super("A célula já está ocupada.");
    }
}
