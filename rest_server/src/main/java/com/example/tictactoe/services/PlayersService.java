package com.example.tictactoe.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.tictactoe.model.Game;
import com.example.tictactoe.model.exception.GameIsFullException;
import com.example.tictactoe.model.exception.NameAlreadyExistsException;
import com.example.tictactoe.model.response.RegistrationResponse;

@Service
public class PlayersService {
    private Map<String, String> playerLabels;
    private String currentPlayerId;
    private static PlayersService instance;
    
    private Game game;

    public PlayersService() {
        playerLabels = new HashMap<String, String>();
        currentPlayerId = "";
        game = Game.getInstance();
    }

    public static PlayersService getInstance() {
        if (instance == null) {
            instance = new PlayersService();
        }

        return instance;
    }

    public RegistrationResponse register(String newPlayerName) {
    	try {
			return RegistrationResponse.ok(game.addPlayer(newPlayerName));
		} catch (GameIsFullException e) {
			return RegistrationResponse.noPlaceAvailable();
		} catch (NameAlreadyExistsException e) {
			return RegistrationResponse.nameAlreadyUsed();
		}
    }

    public boolean isPlayerValid(String playerId) {
        return playerLabels.keySet().contains(playerId);
    }

    public boolean isCurrentPlayer(String playerId) {
        return playerId != null && playerId.equals(currentPlayerId);
    }

    public String getPlayerLabel(String playerId) {
        return playerLabels.get(playerId);
    }

    public void changeCurrentPlayer() {
        for (String player : playerLabels.keySet()) {
            if (!player.equals(currentPlayerId)) {
                currentPlayerId = player;
                break;
            }
        }
    }
}
