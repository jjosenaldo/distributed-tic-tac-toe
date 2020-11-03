package com.example.tictactoe.controllers;

import com.example.tictactoe.model.PlayersService;
import com.example.tictactoe.services.auth.AuthResult;
import com.example.tictactoe.services.auth.Name;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    PlayersService service = new PlayersService();

    @PostMapping(value = "/")
    public @ResponseBody ResponseEntity<Object> register(@RequestBody Name playInfo) {
        AuthResult authResult = service.register(playInfo.getName());

        if (authResult.getStatus().equals("ok")) {
            return new ResponseEntity<>(authResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(authResult, HttpStatus.UNAUTHORIZED);
        }
    }

}
