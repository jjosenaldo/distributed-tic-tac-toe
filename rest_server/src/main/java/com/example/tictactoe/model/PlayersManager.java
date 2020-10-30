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

    boolean isPlayerValid(String playerId) {
        return playerLabels.keySet().contains(playerId);
    }

    boolean isCurrentPlayer(String playerId) {
        return playerId != null && playerId.equals(currentPlayerId);
    }

    String getPlayerLabel(String playerId) {
        return playerLabels.get(playerId);
    }
}
