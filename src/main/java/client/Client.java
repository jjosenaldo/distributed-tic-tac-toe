/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import interfac.HelloWorldI;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author satan
 */
public class Client {
    public static void execute(){
        HelloWorldI helloWorldI = null;
        String response = "";
            try {
                helloWorldI = (HelloWorldI) Naming.lookup("//localhost/MyServer");
            } catch (NotBoundException | MalformedURLException | RemoteException ex) {
                Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(helloWorldI == null) 
                return;
            
            String txt = JOptionPane.showInputDialog("What is your name?");
			
            try {
                response = helloWorldI.hello(txt);
            } catch (RemoteException ex) {
                Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
            }
	
            if(!response.equals(""))
                JOptionPane.showMessageDialog(null, response);
    }
}
