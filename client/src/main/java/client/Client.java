/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi_interface.ServerRemoteI;
import tictactoe.Square;

/**
 *
 * @author satan
 */
public class Client {
    private ServerRemoteI server;
    
    public Client(){
        try {
            server = (ServerRemoteI) Naming.lookup("Server");
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void execute(){
        try {
            Square[][] board = server.getSquares();
            System.out.println(Arrays.toString(board[0]));
            System.out.println(Arrays.toString(board[1]));
            System.out.println(Arrays.toString(board[2]));
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
