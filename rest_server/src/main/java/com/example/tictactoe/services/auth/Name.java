package com.example.tictactoe.services.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Name {
    private String name;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Name(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
