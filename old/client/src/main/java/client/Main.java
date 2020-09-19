/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author satan
 */
public class Main {
	public static void main(String[] args) {
            ClientConnection clientConn = ClientConnection.getInstance();
            clientConn.init();
            Player player = Player.getInstance();
            
            if(!player.register())
                System.exit(0);
	}

}