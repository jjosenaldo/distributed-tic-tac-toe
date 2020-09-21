package server;

import client.ITicTacToeClient;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITicTacToeServer extends Remote{
    Integer registerClient(ITicTacToeClient client, String username) throws RemoteException;
    
    void play(Integer col, Integer row, Integer clientId);
}
