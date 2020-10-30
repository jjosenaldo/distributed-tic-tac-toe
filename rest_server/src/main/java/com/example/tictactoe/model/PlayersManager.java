package com.example.tictactoe.model;

import java.util.HashMap;
import java.util.Map;

public class PlayersManager {
    private Map<String, String> playerLabels;
    private String currentPlayerId;

    public PlayersManager() {
        playerLabels = new HashMap<String, String>();
        currentPlayerId = "";
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
