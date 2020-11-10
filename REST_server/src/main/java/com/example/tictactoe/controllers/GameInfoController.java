package com.example.tictactoe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tictactoe.model.response.InfoResponse;
import com.example.tictactoe.services.GameInfoService;

@RestController
public class GameInfoController {
    @Autowired
    private GameInfoService service;

    @GetMapping("/info")
    public ResponseEntity<InfoResponse> getGameInfo(@RequestParam(value = "token", required = true) String userToken) {
        InfoResponse infoResponse = service.getGameInfo(userToken);
        return new ResponseEntity<>(infoResponse, infoResponse.getHttpStatus());
    }
}
