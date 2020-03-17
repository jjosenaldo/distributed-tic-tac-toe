/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import tictactoe.PlayerChoice;
import tictactoe.Square;
import tictactoe.SquareCode;

/**
 *
 * @author satan
 */
public interface ServerRemoteI extends Remote {
    public Square[][] getSquares() throws RemoteException;
    public SquareCode setSquareAt(int i, int j, Square s) throws RemoteException;
    public PlayerChoice register(ClientRemoteI client) throws RemoteException;
}
