package com.example.tictactoe.services;

import java.util.HashMap;
import java.util.Map;

import com.example.tictactoe.data.Game;
import com.example.tictactoe.services.auth.AuthResult;

public class PlayersService {
    private Map<String, String> playerLabels;
    private String currentPlayerId;
    private static PlayersService instance;
    
    private Game game;

    public PlayersService() {
        playerLabels = new HashMap<String, String>();
        currentPlayerId = "";
        game = new Game();
    }

    public static PlayersService getInstance() {
        if (instance == null) {
            instance = new PlayersService();
        }

        return instance;
    }

    public AuthResult register(String newPlayerName) {
    	if(game.isFull())
    		return AuthResult.noPlaceAvailable();
    	if(game.constains(newPlayerName))
    		return AuthResult.nameAlreadyUsed();
    	return AuthResult.ok(game.addPlayer(newPlayerName));
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
