package gui;

public interface GUI {

    public void showGameScreen(boolean blocked);

    public void showInvalidPlayScreen();

    public void showNotYourTurnScreen();

    public void drawPlay(int row, int col, String label);

    public void finishGameWithWin(int[][] winCoordinates);

    public void finishGameWithDraw();

    public void finishGameWithLoss(int[][] winCoordinates);

    public void goToThisPlayerTurn();

    public void waitOtherPlayerTurn();

    public void setTurnUsername(String username);

}
