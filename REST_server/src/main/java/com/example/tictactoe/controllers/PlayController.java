package com.example.tictactoe.controllers;

import com.example.tictactoe.model.request.PlayInfo;
import com.example.tictactoe.model.response.PlayResponse;
import com.example.tictactoe.services.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayController {
    @Autowired
    private GameService service;

    @PostMapping(value = "/play")
    public @ResponseBody ResponseEntity<PlayResponse> play(@RequestBody PlayInfo playInfo) {
        PlayResponse playResponse = service.play(playInfo.getRow(), playInfo.getColumn(), playInfo.getToken());
        return new ResponseEntity<>(playResponse, playResponse.getHttpStatus());
    }
}
