package com.example.tictactoe.model.response;

import org.springframework.http.HttpStatus;

public class PlayResponse extends Response {
    public PlayResponse(String status, String message, HttpStatus httpStatus) {
        super(status, message, httpStatus);
    }

    public Object getContent() {
        return null;
    }

    public static PlayResponse tokenIsInvalid() {
        return new PlayResponse(Response.ERROR, "O token fornecido é inválido.", HttpStatus.UNAUTHORIZED);
    }

    public static PlayResponse gameHasNotStartedYet() {
        return new PlayResponse(Response.ERROR, "O jogo ainda não começou.", HttpStatus.FORBIDDEN);
    }

    public static PlayResponse indicesAreOutOfBounds() {
        return new PlayResponse(Response.ERROR, "Os índices fornecidos estão fora dos limites do campo.",
                HttpStatus.FORBIDDEN);
    }

    public static PlayResponse notYourTurn() {
        return new PlayResponse(Response.ERROR, "Não é a sua vez.", HttpStatus.FORBIDDEN);
    }

    public static PlayResponse gameIsOver() {
        return new PlayResponse(Response.ERROR, "O jogo já acabou.", HttpStatus.FORBIDDEN);
    }

    public static PlayResponse cellNotAvailable() {
        return new PlayResponse(Response.ERROR, "A célula já está ocupada.", HttpStatus.FORBIDDEN);
    }

    public static PlayResponse ok() {
        return new PlayResponse(Response.OK, "Jogada realizada com sucessso.", HttpStatus.OK);
    }

}