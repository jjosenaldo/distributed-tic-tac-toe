package com.example.tictactoe.controllers;

import com.example.tictactoe.model.GameInfo;
import com.example.tictactoe.services.GameInfoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameInfoController {
    private final GameInfoService service = new GameInfoService();

    @GetMapping("/info")
    public ResponseEntity<GameInfo> getGameInfo(@RequestParam(value = "userId", required = true) int userId,
            @RequestParam(value = "gameId", required = true) int gameId) {
        GameInfo gameInfo = service.getGameInfo(userId, gameId);

        if (gameInfo == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(gameInfo, HttpStatus.OK);
        }
    }
}
