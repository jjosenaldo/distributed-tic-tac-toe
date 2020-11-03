package com.example.tictactoe.controllers;

import com.example.tictactoe.model.PlayInfo;
import com.example.tictactoe.model.PlayStatus;
import com.example.tictactoe.services.GameService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayController {
    private GameService service = GameService.getInstance();

    @PostMapping(value = "/play")
    public @ResponseBody ResponseEntity<Object> play(@RequestBody PlayInfo playInfo) {
        PlayStatus playStatus = service.play(playInfo.getRow(), playInfo.getColumn(), playInfo.getPlayerId(),
                playInfo.getGameId());
        return new ResponseEntity<>(playStatus, HttpStatus.OK);
    }
}
