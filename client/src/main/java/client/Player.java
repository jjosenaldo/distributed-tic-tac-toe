/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tictactoe.PlayerChoice;
import static tictactoe.PlayerChoice.UNAVAILABLE;

/**
 *
 * @author satan
 */
public class Player {
    private static Player instance = null;
    private PlayerChoice choice;
    
    private Player(){
        
    }
    
    public static Player getInstance(){
        if(instance == null) instance = new Player();
        return instance;
    }
    
    public PlayerChoice getChoice(){
        return choice;
    }
    
    public boolean register(){
        try {
            choice = ClientConnection.getInstance().getServer().register(new ClientRemote());
            switch(choice){
                case UNAVAILABLE:{
                    System.out.println("There are already two clients connected!");return false;
                } 
                case CROSS:{
                    System.err.println("You'll be playing as cross!");
                    return true;
                }
                case CIRCLE:{
                    System.err.println("You'll be playing as circle!");
                    return true;
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
