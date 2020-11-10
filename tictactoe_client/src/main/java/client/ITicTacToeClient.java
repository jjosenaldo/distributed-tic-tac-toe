package client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import model.GameStartInfo;
import model.GameStatusAfterPlay;

public interface ITicTacToeClient extends Remote {
    /**
     * Gives this player the necessary information for them to initiate their game.
     * @param info
     * @throws java.rmi.RemoteException 
     */
    public void startGame(GameStartInfo info) throws RemoteException;
    
    /**
     * Gives this player the information about the other player's move.
     * @param row
     * @param col
     * @param gameStatus the status of the game after the other player's move
     * @throws java.rmi.RemoteException
     */
    public void otherPlayerPlayed(int row, int col, GameStatusAfterPlay gameStatus, int[][] winCoordinates) throws RemoteException;
    
    public void playStatus(GameStatusAfterPlay gameStatus, int[][] winCoordinates) throws RemoteException;
}
