package com.example.tictactoe.model.response;

public class PlayResponse extends Response {
    public PlayResponse(String status, String message) {
    	super(status, message);
    }
    
    public Object getContent() {
    	return null;
    }
    
    public static PlayResponse tokenIsInvalid() {
        return new PlayResponse(Response.ERROR, "O token fornecido é inválido.");
    }
    
    public static PlayResponse gameHasNotStartedYet() {
        return new PlayResponse(Response.ERROR, "O jogo ainda não começou.");
    }
    
    public static PlayResponse indicesAreOutOfBounds() {
        return new PlayResponse(Response.ERROR, "Os índices fornecidos estão fora dos limites do campo.");
    }
    
    public static PlayResponse notYourTurn() {
        return new PlayResponse(Response.ERROR, "Não é a sua vez.");
    }
    
    public static PlayResponse gameIsOver() {
        return new PlayResponse(Response.ERROR, "O jogo já acabou.");
    }
    
    public static PlayResponse ok() {
        return new PlayResponse(Response.OK, "Jogada realizada com sucessso.");
    }
}