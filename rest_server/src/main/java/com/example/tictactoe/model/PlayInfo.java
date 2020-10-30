package com.example.tictactoe.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayInfo {
    private final int row;
    private final int column;
    private final int playerId;
    private final int gameId;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PlayInfo(@JsonProperty("row") int row, @JsonProperty("column") int column,
            @JsonProperty("playerId") int playerId, @JsonProperty("gameId") int gameId) {
        this.row = row;
        this.column = column;
        this.playerId = playerId;
        this.gameId = gameId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getGameId() {
        return gameId;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
