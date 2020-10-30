package com.example.tictactoe.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Game {
    private static Game instance;
    private AtomicLong gameId;
    private Board board;
    private PlayersManager playersManager;

    private Game() {
        board = new Board();
        gameId = new AtomicLong(0);
        playersManager = new PlayersManager();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }

        return instance;
    }

    public void updateGameId() {
        gameId.incrementAndGet();
    }

    public PlayStatus play(int row, int column, String playerId, long gameId) {
        if (!isGameIdValid(gameId)) {
            return PlayStatus.invalidGame();
        } else if (!playersManager.isPlayerValid(playerId)) {
            return PlayStatus.invalidPlayer();
        } else if (!playersManager.isCurrentPlayer(playerId)) {
            return PlayStatus.notYourTurn();
        }

        String playerLabel = playersManager.getPlayerLabel(playerId);

        if (!board.applyPlayIfValid(row, column, playerLabel)) {
            return PlayStatus.invalidPlay();
        }

        return PlayStatus.ok();
    }

    private boolean isGameIdValid(long gameId) {
        return this.gameId.get() == gameId;
    }
}
