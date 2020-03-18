/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi_interface.ServerRemoteI;
import tictactoe.SquareCode;

/**
 *
 * @author satan
 */
public class Main {
	public static void main(String[] args) {
            ClientConnection clientConn = ClientConnection.getInstance();
            ServerRemoteI server = clientConn.getServer();
            clientConn.init();
            Player player = Player.getInstance();
            
            if(!player.register())
                System.exit(0);
	}

}