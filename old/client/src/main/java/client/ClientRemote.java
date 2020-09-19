/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi_interface.ClientRemoteI;
import rmi_interface.ServerRemoteI;
import tictactoe.Board;
import tictactoe.SquareCode;

/**
 *
 * @author satan
 */
public class ClientRemote extends UnicastRemoteObject implements ClientRemoteI {
    protected ClientRemote() throws RemoteException {
        super();
    }
    
    @Override
    public void play() throws RemoteException{
        Player player = Player.getInstance();
        ServerRemoteI server = ClientConnection.getInstance().getServer();
        Board board = null;
        
        Scanner in = new Scanner(System.in);
        
        while(board == null){
            int i = in.nextInt();
            int j = in.nextInt();
            try {
                SquareCode code = server.setSquareAt(i, j, player.getChoice());
                switch(code){
                    case OCCUPIED_BY_YOU:{System.out.println("This square is occupied by you!");break;}
                    case OCCUPIED_BY_OPPONENT:{System.out.println("This square is occupied by your opponent!");break;}
                    case INVALID:{System.out.println("Please input a valid position!");break;}
                    case OK:board=server.getBoard();System.out.println(board);
                }

            } catch (RemoteException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(board.isFull()){
            System.out.println("End of game!");
            System.exit(0);
        }
    }
}
