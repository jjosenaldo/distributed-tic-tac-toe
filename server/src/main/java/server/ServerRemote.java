/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import rmi_interface.ServerRemoteI;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import tictactoe.Board;
import tictactoe.Square;
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
    public Square[][] getSquares() throws RemoteException {
        Square[][] ret = Board.getInstance().getSquares();
        Server.getInstance().end();
        return ret;
    }

    @Override
    public SquareCode setSquareAt(int i, int j, Square s) throws RemoteException {
        return Board.getInstance().setSquareAt(i,j,s);
    }

}