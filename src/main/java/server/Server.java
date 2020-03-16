/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author satan
 */
public class Server {
    private final int SHUTDOWN_DELAY_TIME_MS = 2000;
    private UnicastRemoteObject remoteObj;
    private static Server instance = null;
    
    public static Server getInstance(){
        if(instance == null)
            instance = new Server();
        return instance;
    }
    
    public void init(){
        try {            
            remoteObj = new HelloWorldImp();
            Naming.rebind("//localhost/MyServer", remoteObj);
        } catch (MalformedURLException | RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println("Server ready");
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
                    Naming.unbind("//localhost/MyServer");
                } catch (RemoteException | NotBoundException | MalformedURLException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    UnicastRemoteObject.unexportObject(remoteObj, true);
                } catch (NoSuchObjectException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }

          }.start();
    }
}
