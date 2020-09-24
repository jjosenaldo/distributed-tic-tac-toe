package model;

import java.io.Serializable;

public class GameStartInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String otherPlayerLabel;
    private final String otherPlayerUsername;
    private final boolean youStart;
    private final String yourLabel;

    public GameStartInfo(String yourLabel, boolean youStart, String otherPlayerUsername, String otherPlayerLabel) {
        this.yourLabel = yourLabel;
        this.youStart = youStart;
        this.otherPlayerUsername = otherPlayerUsername;
        this.otherPlayerLabel = otherPlayerLabel;
    }

    public boolean youStart() {
        return youStart;
    }

    public String getYourLabel() {
        return yourLabel;
    }

    public String getOtherPlayerLabel() {
        return otherPlayerLabel;
    }

    public String getOtherPlayerUsername() {
        return otherPlayerUsername;
    }
}
