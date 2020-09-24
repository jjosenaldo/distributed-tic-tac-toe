package gui;

public interface GUI {

    public void showGameScreen(boolean blocked);

    public void showInvalidPlayScreen();

    public void showNotYourTurnScreen();

    public void drawOpponentPlay(int row, int col);

    public void finishGameWithWin(int[][] winCoordinates);

    public void finishGameWithDraw();

    public void finishGameWithLoss(int[][] winCoordinates);

    public void goToThisPlayerTurn();

    public void waitOtherPlayerTurn();

    public void setTurnUsername(String username);

}
