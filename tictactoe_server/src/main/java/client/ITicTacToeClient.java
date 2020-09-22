package client;

import model.GameStartInfo;
import model.GameStatus;
import model.TicTacToe;

public interface ITicTacToeClient {
    /**
     * Gives this player the necessary information for them to init their game.
     * @param info
     * @param board 
     */
    void startGame(GameStartInfo info, TicTacToe board);
    
    /**
     * Gives this player the information about the other player's move.
     * @param row
     * @param col
     * @param gameStatus the status of the game after the other player's move
     */
    void otherPlayerPlay(int row, int col, GameStatus gameStatus);
}
