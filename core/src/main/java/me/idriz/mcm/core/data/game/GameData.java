package me.idriz.mcm.core.data.game;

public class GameData {

    private GameTeam winningTeam;
    private final String gameName;
    private final int gameId;

    public GameData(String gameName, int gameId) {
        this.gameName = gameName;
        this.gameId = gameId;
    }

    public GameTeam getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam(GameTeam winningTeam) {
        this.winningTeam = winningTeam;
    }

    /**
     * @return The game name that will be used to uniquely identify gamemodes.
     */
    public String getGameName() {
        return gameName;
    }

    public int getGameId() {
        return gameId;
    }
}
