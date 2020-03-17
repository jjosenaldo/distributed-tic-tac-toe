/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author satan
 */
public class Server {
    private final int SHUTDOWN_DELAY_TIME_MS = 2000;
    private final String NAME = "Server";
    private ServerRemote remoteServerObj;
    private static Server instance = null;
    private Registry registry;
    
    public static Server getInstance(){
        if(instance == null)
            instance = new Server();
        return instance;
    }
    
    public void init(){
        try {            
            remoteServerObj = new ServerRemote();
            registry = LocateRegistry.createRegistry(1099);
            
            // The exportObject() function isn't needed since ServerRemote extends UnicastRemoteObject
            registry.bind(NAME, remoteServerObj);
            System.out.println("Server ready!");
            
        } catch (RemoteException | AlreadyBoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void end(){
        new Thread() {
            @Override
            public void run() {
              System.out.print("Shutting down...");
                try { 
                    sleep(SHUTDOWN_DELAY_TIME_MS);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    registry.unbind(NAME);
                    UnicastRemoteObject.unexportObject(remoteServerObj, true);
                    UnicastRemoteObject.unexportObject(registry, true);
                } catch (RemoteException | NotBoundException  ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.exit(0);
            }

          }.start();
    }
}
