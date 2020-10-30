package com.example.tictactoe.controllers;

import com.example.tictactoe.model.PlayInfo;
import com.example.tictactoe.model.PlayStatus;
import com.example.tictactoe.services.PlayService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayController {
    private PlayService service = new PlayService();

    @PostMapping(value = "/play")
    public @ResponseBody ResponseEntity<Object> play(@RequestBody PlayInfo playInfo) {
        // TODO
        return new ResponseEntity<>(new PlayStatus("vla", "vle"), HttpStatus.OK);
    }
}
