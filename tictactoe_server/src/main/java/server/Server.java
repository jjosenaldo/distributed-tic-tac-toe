package server;

import client.ITicTacToeClient;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ClientInfo;
import model.GameStartInfo;
import model.GameStatusAfterPlay;
import model.TicTacToe;

public class Server extends UnicastRemoteObject implements ITicTacToeServer {

    private final Map<Integer, ClientInfo> clients;
    private Integer currentPlayer;
    private final TicTacToe ticTacToe = new TicTacToe();

    public Server() throws RemoteException {
        super();
        clients = new HashMap<>();
        currentPlayer = null;
    }

    @Override
    public Integer registerClient(ITicTacToeClient remoteInstance, String username) throws RemoteException {
        if (isUsernameAlreadyUsed(username) || isGameAlreadyFull()) {
            return null;
        }

        Integer clientId = generateClientId();

        String label = generateClientLabel(clientId);
        ClientInfo clientInfo = new ClientInfo(username, label, remoteInstance);
        clients.put(clientId, clientInfo);

        if (shouldStartGame()) {
            new Thread(() -> {
                startGame();
            }).start();
        }

        return clientId;
    }

    @Override
    public void play(Integer row, Integer col, Integer clientId) {
        if (!clients.containsKey(clientId)) {

        } else if (currentPlayer == null || !currentPlayer.equals(clientId)) {
            try {
                clients.get(clientId).getRemoteInstance().playStatus(GameStatusAfterPlay.NOT_YOUR_TURN, null);
            } catch (RemoteException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            boolean validPlay = ticTacToe.applyPlayIfValid(row, col, clients.get(clientId).getLabel());

            if (validPlay) {
                int otherPlayerId = getOtherPlayerId(clientId);
                ITicTacToeClient otherPlayerInstance = clients.get(otherPlayerId).getRemoteInstance();

                String winnerLabel = ticTacToe.getWinnerLabelIfGameHasEnded();
                GameStatusAfterPlay gameStatus;
                int[][] winCoordinates = null;

                if (winnerLabel == null) {
                    gameStatus = GameStatusAfterPlay.RUNNING;

                    changeCurrentPlayer(otherPlayerId);
                } else if (winnerLabel.isEmpty()) {
                    gameStatus = GameStatusAfterPlay.DRAW;

                } else {
                    boolean youWon = winnerLabel.equals(clients.get(clientId).getLabel());
                    winCoordinates = ticTacToe.getWinCoordinates();

                    gameStatus = youWon ? GameStatusAfterPlay.PLAYER_WON : GameStatusAfterPlay.PLAYER_LOST;
                }

                try {
                    otherPlayerInstance.otherPlayerPlayed(row, col, gameStatus, winCoordinates);
                } catch (RemoteException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    clients.get(clientId).getRemoteInstance().playStatus(gameStatus, winCoordinates);
                } catch (RemoteException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    clients.get(clientId).getRemoteInstance().playStatus(GameStatusAfterPlay.INVALID_PLAY, null);
                } catch (RemoteException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void startGame() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        List<Entry<Integer, ClientInfo>> clientList = new ArrayList<>(clients.entrySet());

        for (int i = 0; i < clientList.size(); ++i) {
            Integer clientId = clientList.get(i).getKey();
            ClientInfo clientInfo = clientList.get(i).getValue();
            ClientInfo otherClientInfo = clientList.get(1 - i).getValue();

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

    private int getFirstPlayerId() {
        return 0;
    }

    private boolean isUsernameAlreadyUsed(String username) {
        Optional<Entry<Integer, ClientInfo>> clientWithGivenUsername
                = clients.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().getUsername().equals(username))
                        .findFirst();

        return clientWithGivenUsername.isPresent();
    }

    private boolean isGameAlreadyFull() {
        return shouldStartGame();
    }

    private boolean shouldStartGame() {
        return clients.size() == 2;
    }

    private String generateClientLabel(Integer clientId) {
        if (clientId == 0) {
            return "X";
        } else {
            return "O";
        }
    }

    private Integer generateClientId() {
        return clients.size();
    }

    private void changeCurrentPlayer(int otherPlayerId) {
        currentPlayer = otherPlayerId;
    }

    private int getOtherPlayerId(int clientId) {
        return 1 - clientId;
    }
}
