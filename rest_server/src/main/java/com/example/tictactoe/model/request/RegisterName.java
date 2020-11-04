package com.example.tictactoe.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterName {
    private String name;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RegisterName(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
