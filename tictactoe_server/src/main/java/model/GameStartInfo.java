package model;

import java.io.Serializable;

public class GameStartInfo implements Serializable {
    private final String otherPlayerLabel;
    private final String otherPlayerUsername;
    private final boolean youStart;
    private final String yourLabel;
    
    public GameStartInfo(String yourLabel, boolean youStart, String otherPlayerUsername, String otherPlayerLabel){
        this.yourLabel = yourLabel;
        this.youStart = youStart;
        this.otherPlayerUsername = otherPlayerUsername;
        this.otherPlayerLabel = otherPlayerLabel;
    }
    
    boolean youStart(){
        return youStart;
    }
    
    String getYourLabel(){
        return yourLabel;
    }
    
    String getOtherPlayerLabel(){
        return otherPlayerLabel;
    }
    
    String getOtherPlayerUsername(){
        return otherPlayerUsername;
    }
}
