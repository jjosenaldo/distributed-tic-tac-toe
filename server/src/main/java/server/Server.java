/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import rmi_interface.ClientRemoteI;
import tictactoe.PlayerChoice;

/**
 *
 * @author satan
 */
public class Server {
    private ClientRemoteI clientCross, clientCircle;
    private static Server instance = null;
    
    private Server(){
        clientCross = null;
        clientCircle = null;
    }
    
    public static Server getInstance(){
        if(instance == null) instance = new Server();
        return instance;
    }
    
    public PlayerChoice register(ClientRemoteI client){
        if(clientCross == null) {clientCross = client;return PlayerChoice.CROSS;}
        if(clientCircle == null) {clientCircle = client;return PlayerChoice.CIRCLE;}
        return PlayerChoice.UNAVAILABLE;
    }
}
