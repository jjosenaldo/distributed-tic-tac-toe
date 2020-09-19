/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author satan
 */
public interface ClientRemoteI extends Remote{
    public void play() throws RemoteException;
}
