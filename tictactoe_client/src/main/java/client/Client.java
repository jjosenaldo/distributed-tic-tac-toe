package client;

import gui.GUI;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import model.GameStartInfo;
import model.GameStatusAfterPlay;
import model.TicTacToe;
import server.ITicTacToeServer;

public class Client extends UnicastRemoteObject implements ITicTacToeClient, IClientController {

    private GUI gui;
    private final ITicTacToeServer server;
    private String yourUsername;
    private String yourLabel;
    private String otherPlayerUsername;
    private String otherPlayerLabel;
    private Integer myId;

    public Client(ITicTacToeServer server) throws RemoteException {
        super();
        this.server = server;
    }

    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void play(int row, int col) {
        try {
            server.play(row, col, myId);
        } catch (RemoteException e) {
        }
    }

    public String getYourUsername() {
        return yourUsername;
    }

    @Override
    public String getYourLabel() {
        return yourLabel;
    }

    public String getOtherPlayerUsername() {
        return otherPlayerUsername;
    }

    public String getOtherPlayerLabel() {
        return otherPlayerLabel;
    }

    @Override
    public void startGame(GameStartInfo info, TicTacToe board) throws RemoteException {
        this.yourLabel = info.getYourLabel();
        this.otherPlayerLabel = info.getOtherPlayerLabel();
        this.otherPlayerUsername = info.getOtherPlayerUsername();

        if (info.youStart()) {
            // TODO
            // gui.setYourTurn(this.yourUsername); // Habilita o tabuleiro
        } else {
            // TODO
            // gui.setOtherPlayerTurn(this.otherPlayUsername); // Bloqueia o tabuleiro
        }
        // TODO
        // gui.initGame();
    }

    @Override
    public void otherPlayerPlayed(int row, int col, GameStatusAfterPlay gameStatus, int[][] winCoordinates) throws RemoteException {
        gui.drawOpponentPlay(row, col);

        switch (gameStatus) {
            case RUNNING:
                gui.goToThisPlayerTurn();
                break;
            case PLAYER_WON:
                gui.finishGameWithLoss(winCoordinates);
                break;
            case PLAYER_LOST:
                gui.finishGameWithWin(winCoordinates);
                break;
            case DRAW:
                gui.finishGameWithDraw();
        }
    }

    @Override
    public void playStatus(GameStatusAfterPlay gameStatus, int[][] winCoordinates) {
        switch (gameStatus) {
            case INVALID_PLAY:
                gui.showInvalidPlayScreen();
                break;
            case NOT_YOUR_TURN:
                gui.showNotYourTurnScreen();
                break;
            case PLAYER_WON:
                gui.finishGameWithWin(winCoordinates);
                break;
            case PLAYER_LOST:
                gui.finishGameWithLoss(winCoordinates);
                break;
            case DRAW:
                gui.finishGameWithDraw();
                break;
            case RUNNING:
                gui.waitOtherPlayerTurn();
                break;
        }
    }

    @Override
    public boolean register(String username) {
        try {
            Integer id = server.registerClient(this, username);
            if (id != null) {
                this.myId = id;
                this.yourUsername = username;
                return true;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }
}
