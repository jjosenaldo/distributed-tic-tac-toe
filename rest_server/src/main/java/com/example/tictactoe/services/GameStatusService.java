package com.example.tictactoe.services;

import org.springframework.stereotype.Service;

import com.example.tictactoe.model.Game;
import com.example.tictactoe.model.exception.TokenDoesNotExistException;
import com.example.tictactoe.model.response.StatusResponse;

@Service
public class GameStatusService {
	private Game game;
	
	public GameStatusService() {
		game = Game.getInstance();
	}

    public StatusResponse getGameStatus(String token) {
    	try {
			return StatusResponse.ok(game.getGameStatus(token));
		} catch (TokenDoesNotExistException e) {
			return StatusResponse.tokenIsInvalid();
		}
    }
}