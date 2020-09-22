package server;

import client.ITicTacToeClient;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ClientInfo;
import model.GameInfo;
import model.TicTacToe;

public class Server extends UnicastRemoteObject implements ITicTacToeServer{
    private final Map<Integer, ClientInfo> clients;
    private Integer currentPlayer; 
    private TicTacToe ticTacToe;
    
    public Server() throws RemoteException {
        clients = new HashMap<>();
        currentPlayer = null;
    }

    @Override
    public Integer registerClient(ITicTacToeClient remoteInstance, String username) throws RemoteException {
        // TODO: check if the username is already being used, and if it is, return null
        // TODO: if the game is running, this method should return null
        
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
    public Boolean play(Integer row, Integer col, Integer clientId) {
        if(clients.containsKey(clientId) && currentPlayer != null && currentPlayer.equals(clientId)){
            boolean validPlay = ticTacToe.applyPlayIfValid(row, col, clients.get(clientId).getLabel());
            
            if(validPlay){
                String winnerLabel = ticTacToe.getWinnerLabelIfGameHasEnded();
                
                if(winnerLabel == null){
                    // TODO
                }
                else if(winnerLabel.isEmpty()){
                    // TODO
                } else{
                    // TODO
                }
                
                return true;
            } else{
                return false;
            }
            
        } else{
            return null;
        }
    }
    
    private void changeCurrentPlayer(){
        currentPlayer = clients.entrySet()
                .stream()
                .filter(entry -> !entry.getKey().equals(currentPlayer))
                .findFirst()
                .get().getKey();
    }   
    
    private void startGame() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        
        // Calls the startGame() method in each client
        clients.forEach((clientId, clientInfo) -> {
            String otherPlayerUsername;

            if(clientId == 0){
                otherPlayerUsername = clients.get(1).getUsername();
            } else{
                otherPlayerUsername = clients.get(0).getUsername();
            }
            
            boolean youStart = clientId == getFirstPlayerId();
            
            GameInfo gameInfo = new GameInfo(otherPlayerUsername,youStart, clientInfo.getLabel());
            
            new Thread(() -> {
                clientInfo.getRemoteInstance().startGame(gameInfo, ticTacToe);
                countDownLatch.countDown();
            }).start(); 
        });
        
        try {
            countDownLatch.await();
            gameLoop();
        } catch (InterruptedException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void gameLoop(){
        currentPlayer = getFirstPlayerId();
        
        while(true){}
    }
    
    private int getFirstPlayerId(){
        return 0;
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
