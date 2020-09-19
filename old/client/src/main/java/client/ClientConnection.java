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
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi_interface.ServerRemoteI;

/**
 *
 * @author satan
 */
public class ClientConnection {
    private ServerRemoteI server;
    private static ClientConnection instance = null;
    
    private ClientConnection(){
        
    }
    
    public static ClientConnection getInstance(){
        if(instance == null) instance = new ClientConnection();
        return instance;
    }
    
    public ServerRemoteI getServer(){
        return server;
    }
    
    public void init(){
        try {
            server = (ServerRemoteI) Naming.lookup("Server");
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
