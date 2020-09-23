package client;

import gui.GUI;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import model.GameStartInfo;
import model.GameStatusAfterPlay;
import model.TicTacToe;

public class Client extends UnicastRemoteObject implements ITicTacToeClient{    
    private final GUI gui;
    
    public Client(GUI gui) throws RemoteException {
        super();
        this.gui = gui;
    }

    @Override
    public void startGame(GameStartInfo info, TicTacToe board) throws RemoteException {
        gui.showGameHomeScreen(info, board);
    }

    @Override
    public void otherPlayerPlayed(int row, int col, GameStatusAfterPlay gameStatus, int[][] winCoordinates) throws RemoteException {
        gui.drawOpponentPlay(row, col);
        
        switch(gameStatus){
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
    public void playStatus(GameStatusAfterPlay gameStatus, int[][] winCoordinates){
        switch(gameStatus){
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
}
