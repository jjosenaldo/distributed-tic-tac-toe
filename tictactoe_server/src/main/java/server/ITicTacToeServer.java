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
    Integer registerClient(ITicTacToeClient client, String username) throws RemoteException;
    
    /**
     * 
     * @param col
     * @param row
     * @param clientId
     * @return null if the client is not on their turn, 
     *         false if the client was on their turn, but the play was invalid,
     *         true if the play was successful
     */
    Boolean play(Integer col, Integer row, Integer clientId);
}
