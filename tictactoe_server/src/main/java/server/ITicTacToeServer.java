package server;

import client.ITicTacToeClient;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITicTacToeServer extends Remote{
    /**
     * Registers a new client in the game.
     * @param client the client's remote object
     * @param username the client's username
     * @return the client's id to be used in subsequent calls to the server, if
     *         the registration was successful,
     *         null otherwise
     * @throws RemoteException 
     */
    public Integer registerClient(ITicTacToeClient client, String username) throws RemoteException;
    
    /**
     * 
     * @param col
     * @param row
     * @param clientId
     * @return null if the client is not playing,
     *         the status of the game after the player's play, otherwise
     * @throws java.rmi.RemoteException
     */
    public void play(Integer row, Integer col, Integer clientId) throws RemoteException;
}
