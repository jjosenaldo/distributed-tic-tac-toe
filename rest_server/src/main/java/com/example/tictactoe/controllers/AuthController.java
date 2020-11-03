package com.example.tictactoe.controllers;

import com.example.tictactoe.model.PlayStatus;
import com.example.tictactoe.services.AuthService;
import com.example.tictactoe.services.auth.Name;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    AuthService service = new AuthService();

    @PostMapping(value = "/")
    public @ResponseBody ResponseEntity<Object> register(@RequestBody Name playInfo) {
        PlayStatus playStatus = service.play(playInfo.getRow(), playInfo.getColumn(), playInfo.getPlayerId(),
                playInfo.getGameId());
        return new ResponseEntity<>(playStatus, HttpStatus.OK);
    }

}
