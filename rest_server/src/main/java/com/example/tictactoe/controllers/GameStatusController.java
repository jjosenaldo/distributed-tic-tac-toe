package com.example.tictactoe.controllers;

import com.example.tictactoe.model.GameStatus;
import com.example.tictactoe.services.GameStatusService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameStatusController {
    private final GameStatusService service = new GameStatusService();

    @GetMapping("/status")
    public ResponseEntity<GameStatus> getGameStatus(@RequestParam(value = "id", required = true) int id) {
        GameStatus gameStatus = service.getGameStatus(id);

        if (gameStatus == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(gameStatus, HttpStatus.OK);
        }
    }
}
