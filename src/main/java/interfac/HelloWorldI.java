/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfac;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author satan
 */
public interface HelloWorldI extends Remote {

    public String hello(String name) throws RemoteException;

}