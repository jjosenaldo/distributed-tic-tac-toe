/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.MalformedURLException;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import interfac.HelloWorldI;

/**
 *
 * @author satan
 */
public class HelloWorldImp extends UnicastRemoteObject implements HelloWorldI{

    private static final long serialVersionUID = 1L;

    protected HelloWorldImp() throws RemoteException {

        super();

    }

    @Override
    public String hello(String name) throws RemoteException, NoSuchObjectException{

        System.err.println(name + " is trying to contact!");
        if(name.equals("exit")) {
            Server.getInstance().end();
            return "";
        }
        
        return "Server says hello to " + name;

    }
}