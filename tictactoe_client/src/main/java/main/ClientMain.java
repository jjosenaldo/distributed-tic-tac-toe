package main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import client.Client;
import client.ITicTacToeClient;
import gui.GUI;
import server.ITicTacToeServer;

public class ClientMain {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 1098;
    private static final String OBJECT_NAME = "Server";
    
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException{
        GUI gui = null;
        ITicTacToeServer server = (ITicTacToeServer) Naming.lookup("rmi://" + ADDRESS + ":" + PORT +"/"+OBJECT_NAME);
        ITicTacToeClient client = new Client(gui);
    }
}
