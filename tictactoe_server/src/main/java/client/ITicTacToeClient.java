package client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import model.GameStartInfo;
import model.GameStatusAfterPlay;
import model.TicTacToe;

public interface ITicTacToeClient extends Remote {
    /**
     * Gives this player the necessary information for them to init their game.
     * @param info
     * @param board 
     * @throws java.rmi.RemoteException 
     */
    void startGame(GameStartInfo info, TicTacToe board) throws RemoteException;
    
    /**
     * Gives this player the information about the other player's move.
     * @param row
     * @param col
     * @param gameStatus the status of the game after the other player's move
     * @throws java.rmi.RemoteException
     */
    void otherPlayerPlayed(int row, int col, GameStatusAfterPlay gameStatus) throws RemoteException;
}
