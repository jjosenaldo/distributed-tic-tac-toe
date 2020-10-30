package com.example.tictactoe.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Game {
    private static Game instance;
    private AtomicLong gameId;
    private Board board;
    private Map<String, String> playerMarks;

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
        // TODO
        return null;
    }

    String[][] getBoard() {
        return board.board;
    }

    private class Board {
        String[][] board;

        private Board() {
            for (int row = 0; row < 3; ++row)
                for (int col = 0; col < 3; ++col)
                    board[row][col] = "";
        }
    }
}
