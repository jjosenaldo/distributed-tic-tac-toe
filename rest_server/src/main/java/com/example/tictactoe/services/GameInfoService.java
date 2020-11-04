package com.example.tictactoe.services;

import org.springframework.stereotype.Service;

import com.example.tictactoe.model.Game;
import com.example.tictactoe.model.exception.GameHasNotStartedException;
import com.example.tictactoe.model.exception.TokenDoesNotExistException;
import com.example.tictactoe.model.response.InfoResponse;

@Service
public class GameInfoService {
	private Game game;
	
	public GameInfoService() {
		game = Game.getInstance();
	}
	
    public InfoResponse getGameInfo(String token) {
    	try {
			return InfoResponse.ok(game.getGameInfo(token));
		} catch (TokenDoesNotExistException e) {
			return InfoResponse.tokenIsInvalid();
		} catch (GameHasNotStartedException e) {
			return InfoResponse.gameHasNotStartedYet();
		}
    }
}
