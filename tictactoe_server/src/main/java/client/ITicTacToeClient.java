package client;

import model.GameStartInfo;
import model.TicTacToe;

public interface ITicTacToeClient {
    void startGame(GameStartInfo info, TicTacToe board);
}
