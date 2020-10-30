package com.example.tictactoe.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayStatus {
    private final String status;
    private final String message;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PlayStatus(@JsonProperty("status") String status, @JsonProperty("message") String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public static PlayStatus invalidGame() {
        return new PlayStatus("error", "Jogo inválido.");
    }

    public static PlayStatus invalidPlayer() {
        return new PlayStatus("error", "Jogador inválido.");
    }

    public static PlayStatus notYourTurn() {
        return new PlayStatus("error", "O jogo não está na sua vez.");
    }

    public static PlayStatus invalidPlay() {
        return new PlayStatus("error", "Jogada inválida.");
    }

    public static PlayStatus ok() {
        return new PlayStatus("ok", "");
    }
}
