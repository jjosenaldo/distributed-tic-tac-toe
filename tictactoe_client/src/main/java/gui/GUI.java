package gui;

import model.GameStartInfo;
import model.TicTacToe;

public interface GUI {
    public void showGameHomeScreen(GameStartInfo info, TicTacToe board);
    
    public void showInvalidPlayScreen();
    
    public void showNotYourTurnScreen();
    
    public void drawOpponentPlay(int row, int col);
    
    public void finishGameWithWin(int[][] winCoordinates);
    
    public void finishGameWithDraw();
    
    public void finishGameWithLoss(int[][] winCoordinates);
    
    public void goToThisPlayerTurn();
    
    public void waitOtherPlayerTurn();
}
