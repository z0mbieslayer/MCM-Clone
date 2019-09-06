package me.idriz.mcm.core.data;

import me.idriz.mcm.core.data.game.GameData;
import me.idriz.mcm.core.data.game.GameTeam;

import java.util.HashSet;
import java.util.Set;

public class Tournament {

    private final int id;

    private Set<GameTeam> teams = new HashSet<>();
    private Set<GameData> games = new HashSet<>();

    public Tournament(int id) {
        this.id = id;
    }

    public Set<GameTeam> getTeams() {
        return teams;
    }

    public void setTeams(Set<GameTeam> teams) {
        this.teams = teams;
    }

    public Set<GameData> getGames() {
        return games;
    }

    public void setGames(Set<GameData> games) {
        this.games = games;
    }

    public int getId() {
        return id;
    }
}
