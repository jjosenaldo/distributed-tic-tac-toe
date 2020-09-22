package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMain {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 1098;
    private static final String OBJECT_NAME = "Server";
    
    public static void main(String[] args){
        System.setProperty("java.rmi.server.hostname", ADDRESS);
		
        ITicTacToeServer server;
        try {
            server = new Server();
            LocateRegistry.createRegistry(PORT);
            Naming.rebind("rmi://" + ADDRESS + ":" + String.valueOf(PORT)+"/"+OBJECT_NAME, server);
            System.out.println("RMI server started");
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
