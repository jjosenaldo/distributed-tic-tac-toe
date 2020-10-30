package com.example.tictactoe.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Game {
    private static Game instance;
    private AtomicLong gameId;
    private Board board;
    private Map<String, String> playerMarks;
    private String currentPlayerId;

    private Game() {
        board = new Board();
        playerMarks = new HashMap<String, String>();
        gameId = new AtomicLong(0);
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
        if (this.gameId.get() != gameId) {
            return PlayStatus.invalidGame();
        } else if (!isPositionInsideBoardBounds(row, column)) {
            return PlayStatus.invalidBoardPosition();
        }
        return null;
    }

    private boolean isPositionInsideBoardBounds(int row, int column) {
        return row >= 0 && row < 3 && column >= 0 && column < 3;
    }
}
