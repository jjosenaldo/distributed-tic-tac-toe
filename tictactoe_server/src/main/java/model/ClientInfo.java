package model;

import client.ITicTacToeClient;


public class ClientInfo {
    private final String username;
    private final String label;
    private final ITicTacToeClient remoteInstance;
    
    public ClientInfo(String username, String label, ITicTacToeClient remoteInstance){
        this.username = username;
        this.label = label;
        this.remoteInstance = remoteInstance;
    }
    
    public ITicTacToeClient getRemoteInstance(){
        return remoteInstance;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getLabel(){
        return label;
    }
}
