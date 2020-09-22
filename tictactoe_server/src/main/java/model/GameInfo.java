package model;

public class GameInfo {
    private final String otherPlayer;
    private final boolean youStart;
    private final String yourLabel;
    
    public GameInfo(String otherPlayer, boolean youStart, String yourLabel){
        this.otherPlayer = otherPlayer;
        this.youStart = youStart;
        this.yourLabel = yourLabel;
    }
}
