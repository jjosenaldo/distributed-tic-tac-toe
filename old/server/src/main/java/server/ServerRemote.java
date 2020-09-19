/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import rmi_interface.ServerRemoteI;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import rmi_interface.ClientRemoteI;
import tictactoe.Board;
import tictactoe.PlayerChoice;
import tictactoe.SquareCode;

/**
 *
 * @author satan
 */
public class ServerRemote extends UnicastRemoteObject implements ServerRemoteI{
    protected ServerRemote() throws RemoteException {
        super();
    }
    
    @Override
    public Board getBoard() throws RemoteException {
        return Board.getInstance();
    }

    @Override
    public SquareCode setSquareAt(int i, int j, PlayerChoice choice) throws RemoteException {
        return Board.getInstance().setSquareAt(i,j,choice);
    }
    
    @Override
    public PlayerChoice register(ClientRemoteI client) throws RemoteException{
        return Server.getInstance().register(client);
    }

}