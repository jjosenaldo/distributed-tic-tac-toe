package com.example.tictactoe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tictactoe.model.response.StatusResponse;
import com.example.tictactoe.services.GameStatusService;

@RestController
public class GameStatusController {
    @Autowired
    private GameStatusService service;

    @GetMapping("/status")
    public ResponseEntity<StatusResponse> getGameStatus(@RequestParam(value = "token", required = true) String token) {
        StatusResponse statusResponse = service.getGameStatus(token);
        return new ResponseEntity<>(statusResponse, statusResponse.getHttpStatus());
    }
}
