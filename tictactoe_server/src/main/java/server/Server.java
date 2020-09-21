package server;

import client.ITicTacToeClient;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import model.ClientInfo;
import model.GameInfo;

public class Server extends UnicastRemoteObject implements ITicTacToeServer{
    private final Map<Integer, ClientInfo> clients;
    private Integer currentClientPlaying; 
    
    public Server() throws RemoteException {
        clients = new HashMap<>();
        currentClientPlaying = null;
    }

    @Override
    public Integer registerClient(ITicTacToeClient remoteInstance, String username) throws RemoteException {
        Integer clientId = generateClientId();
        
        if(clientId != null){
            String label = generateClientLabel(clientId);
            ClientInfo clientInfo = new ClientInfo(username, label, remoteInstance);
            clients.put(clientId, clientInfo);
            
            if(shouldStartGame()){
               new Thread(() -> {
                    startGame();
                }).start(); 
            }
        }
                
        return clientId;
    }
    
    @Override
    public void play(Integer col, Integer row, Integer clientId) {
        if(currentClientPlaying != null){
            if(currentClientPlaying.equals(clientId)){
                
            }
        } else{
            
        }
    }
    
    private void startGame(){
        clients.forEach((clientId, clientInfo) -> {
            GameInfo gameInfo;

            if(clientId == 0){
                gameInfo = new GameInfo(clients.get(1).getUsername());
            } else{
                gameInfo = new GameInfo(clients.get(0).getUsername());
            }
            
            new Thread(() -> {
                clientInfo.getRemoteInstance().setGameInfo(gameInfo);
            }).start(); 
        });
    }
    
    private boolean shouldStartGame(){
        return clients.size() == 2;
    }
    
    private String generateClientLabel(Integer clientId){
        if(clientId == 0){
            return "X";
        } else{
            return "O";
        }
    }
    
    private Integer generateClientId(){
        if(clients.size() == 2){
            return null;
        } else{
            return clients.size();
        }
    }
}
