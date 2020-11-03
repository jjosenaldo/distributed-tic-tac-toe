package com.example.tictactoe.model;

import java.util.HashMap;
import java.util.Map;

import com.example.tictactoe.services.auth.AuthResult;

public class PlayersManager {
    private Map<String, String> playerLabels;
    private String currentPlayerId;
    private static PlayersManager instance;

    private PlayersManager() {
        playerLabels = new HashMap<String, String>();
        currentPlayerId = "";
    }

    public static PlayersManager getInstance() {
        if (instance == null) {
            instance = new PlayersManager();
        }

        return instance;
    }

    public AuthResult register(String newPlayerName) {
        if (playerLabels.size() > 1) {
            return AuthResult.noPlaceAvailable();
        } else {
            for (String name : playerLabels.values()) {
                if (name.equals(newPlayerName)) {
                    return AuthResult.nameAlreadyUsed();
                }
            }

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
