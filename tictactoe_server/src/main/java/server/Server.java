package server;

import client.ITicTacToeClient;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ClientInfo;
import model.GameStartInfo;
import model.GameStatusAfterPlay;
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
    public GameStatusAfterPlay play(Integer row, Integer col, Integer clientId) {
        if(!clients.containsKey(clientId)){
            return null;
        } else if(currentPlayer == null || !currentPlayer.equals(clientId)){
            return GameStatusAfterPlay.NOT_YOUR_TURN;
        } else{
            boolean validPlay = ticTacToe.applyPlayIfValid(row, col, clients.get(clientId).getLabel());
            
            if(validPlay){
                int otherPlayerId = getOtherPlayerId(clientId);
                ITicTacToeClient otherPlayerInstance = clients.get(otherPlayerId).getRemoteInstance();
            
                String winnerLabel = ticTacToe.getWinnerLabelIfGameHasEnded();
                GameStatusAfterPlay gameStatus;
                
                if(winnerLabel == null){
                    gameStatus = GameStatusAfterPlay.RUNNNING;
                    
                    changeCurrentPlayer(otherPlayerId);
                } else if(winnerLabel.isEmpty()){
                    gameStatus = GameStatusAfterPlay.DRAW;
                    
                    new Thread(() -> {
                        endGame();
                    }).start(); 
                } else{
                    boolean youWon = winnerLabel.equals(clients.get(clientId).getLabel());
                    
                    gameStatus = youWon ? GameStatusAfterPlay.PLAYER_WON : GameStatusAfterPlay.PLAYER_LOST;
                    
                    new Thread(() -> {
                        endGame();
                    }).start(); 
                }
                
                try {
                    otherPlayerInstance.otherPlayerPlay(row, col, gameStatus);
                } catch (RemoteException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                return gameStatus;
            } else{
                return GameStatusAfterPlay.INVALID_PLAY;
            }
        }   
    }
    
    private void startGame() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        List<Entry<Integer, ClientInfo>> clientList = new ArrayList<>(clients.entrySet());
        
        for(int i = 0; i < clientList.size(); ++i){
            Integer clientId = clientList.get(i).getKey();
            ClientInfo clientInfo = clientList.get(i).getValue();
            ClientInfo otherClientInfo = clientList.get(1-i).getValue();
            
            // Builds GameStartInfo object for the current client in the loop
            String otherPlayerUsername = otherClientInfo.getUsername();
            String otherPlayerLabel = otherClientInfo.getLabel();
            boolean youStart = clientId == getFirstPlayerId();

            GameStartInfo gameInfo = new GameStartInfo(clientInfo.getLabel(), youStart, otherPlayerUsername, otherPlayerLabel);
            
            // Calls the startGame() method in the current client's instance (in another thread)
            new Thread(() -> {
                try {
                    clientInfo.getRemoteInstance().startGame(gameInfo, ticTacToe);
                } catch (RemoteException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                countDownLatch.countDown();
            }).start(); 
        }
        
        try {
            countDownLatch.await();
            currentPlayer = getFirstPlayerId();
        } catch (InterruptedException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void endGame(){
        throw new UnsupportedOperationException("TODO");
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
    
     private void changeCurrentPlayer(int otherPlayerId){
        currentPlayer = otherPlayerId;
    }
     
    private int getOtherPlayerId(int clientId){
        return 1-clientId;
    }
}
