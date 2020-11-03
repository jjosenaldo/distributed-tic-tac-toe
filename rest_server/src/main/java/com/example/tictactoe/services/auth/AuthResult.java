package com.example.tictactoe.services.auth;

public class AuthResult {
    private String status;
    private String message;
    private String playerId;

    public AuthResult(String status, String message, String playerId) {
        this.setStatus(status);
        this.setMessage(message);
        this.setPlayerId(playerId);
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static AuthResult noPlaceAvailable() {
        return new AuthResult("error", "Não há vagas disponíveis.", null);
    }

    public static AuthResult nameAlreadyUsed() {
        return new AuthResult("error", "O nome informado já está em uso.", null);
    }

    public static AuthResult ok() {
        return new AuthResult("ok", "", null);
    }

}
