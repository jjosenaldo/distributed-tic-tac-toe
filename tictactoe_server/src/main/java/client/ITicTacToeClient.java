package client;

import model.GameInfo;
import model.TicTacToe;

public interface ITicTacToeClient {
    void startGame(GameInfo info, TicTacToe board);
}
