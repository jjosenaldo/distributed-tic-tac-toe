package com.example.tictactoe.services;

import java.util.concurrent.atomic.AtomicLong;

import com.example.tictactoe.model.Board;
import com.example.tictactoe.model.PlayStatus;
import com.example.tictactoe.model.PlayersService;

public class GameService {
    private static GameService instance;
    private AtomicLong gameId;
    private Board board;
    private PlayersService playersManager;

    private GameService() {
        board = new Board();
        playersManager = PlayersService.getInstance();
    }

    public static GameService getInstance() {
        if (instance == null) {
            instance = new GameService();
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

        playersManager.changeCurrentPlayer();

        return PlayStatus.ok();
    }

    private boolean isGameIdValid(long gameId) {
        return this.gameId != null && this.gameId.get() == gameId;
    }
}
