package com.example.tictactoe.controllers;

import com.example.tictactoe.model.request.RegisterName;
import com.example.tictactoe.model.response.RegistrationResponse;
import com.example.tictactoe.model.response.Response;
import com.example.tictactoe.services.PlayersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
	@Autowired
    private PlayersService service;

    @PostMapping(value = "/register")
    public @ResponseBody ResponseEntity<Object> register(@RequestBody RegisterName playInfo) {
        RegistrationResponse authResponse = service.register(playInfo.getName());
        
        if (authResponse.getStatus().equals(Response.OK))
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        else
            return new ResponseEntity<>(authResponse, HttpStatus.UNAUTHORIZED);
    }
}
