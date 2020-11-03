package com.example.tictactoe.services;

import java.util.HashMap;
import java.util.Map;

import com.example.tictactoe.services.auth.AuthResult;

public class PlayersService {
    private Map<String, String> playerLabels;
    private String currentPlayerId;
    private static PlayersService instance;

    public PlayersService() {
        playerLabels = new HashMap<String, String>();
        currentPlayerId = "";
    }

    public static PlayersService getInstance() {
        if (instance == null) {
            instance = new PlayersService();
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

            // TODO: de fato registrar o usuário
            return AuthResult.ok();
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
