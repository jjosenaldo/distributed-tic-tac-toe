package com.example.tictactoe.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayInfo {
    private final int row;
    private final int column;
    private final String token;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PlayInfo(@JsonProperty("row") int row, 
    		@JsonProperty("column") int column,
            @JsonProperty("token") String token) {
        this.row = row;
        this.column = column;
        this.token = token;
    }
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public String getToken() {
        return token;
    }
}
