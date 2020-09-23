package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import model.GameStartInfo;
import model.GameStatusAfterPlay;
import model.TicTacToe;
import server.ITicTacToeServer;

/**
 *
 * @author satan
 */
public class Client extends UnicastRemoteObject implements ITicTacToeClient{    
    ITicTacToeServer server;
    
    public Client() throws RemoteException {
        super();
    }

    @Override
    public void startGame(GameStartInfo info, TicTacToe board) throws RemoteException {
        System.out.println("startGame()");
    }

    @Override
    public void otherPlayerPlayed(int row, int col, GameStatusAfterPlay gameStatus, int[][] winCoordinates) throws RemoteException {
        System.out.println("otherPlayerPlayed(): " + row + " " +  col + " " + gameStatus);
    }
    
    @Override
    public void playStatus(GameStatusAfterPlay gameStatus, int[][] winCoordinates){
        System.out.println("playStatus()");
    }
    
    public void setUsername(String username){
        
    }
}
