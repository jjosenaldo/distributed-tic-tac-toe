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
}
