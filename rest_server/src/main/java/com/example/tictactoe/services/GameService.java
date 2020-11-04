package com.example.tictactoe.services;

import org.springframework.stereotype.Service;

import com.example.tictactoe.model.Game;
import com.example.tictactoe.model.exception.GameHasNotStartedException;
import com.example.tictactoe.model.exception.GameIsOverException;
import com.example.tictactoe.model.exception.NotYourTurnException;
import com.example.tictactoe.model.exception.OutOfBoardException;
import com.example.tictactoe.model.exception.TokenDoesNotExistException;
import com.example.tictactoe.model.response.PlayResponse;

@Service
public class GameService {
    private Game game;
    
    private GameService() {
        game = Game.getInstance();
    }

    public PlayResponse play(int row, int column, String token) {
    	try {
			game.play(row, column, token);
			return PlayResponse.ok();
		} catch (TokenDoesNotExistException e) {
			return PlayResponse.tokenIsInvalid();
		} catch (GameHasNotStartedException e) {
			return PlayResponse.gameHasNotStartedYet();
		} catch (OutOfBoardException e) {
			return PlayResponse.indicesAreOutOfBounds();
		} catch (NotYourTurnException e) {
			return PlayResponse.notYourTurn();
		} catch (GameIsOverException e) {
			return PlayResponse.gameIsOver();
		}
    }
}
