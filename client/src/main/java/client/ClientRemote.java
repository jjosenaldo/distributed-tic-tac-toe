/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import rmi_interface.ClientRemoteI;

/**
 *
 * @author satan
 */
public class ClientRemote extends UnicastRemoteObject implements ClientRemoteI {
    protected ClientRemote() throws RemoteException {
        super();
    }
}
