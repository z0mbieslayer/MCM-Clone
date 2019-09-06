package me.idriz.mcm.core.data.game;

import org.bukkit.ChatColor;

import java.util.HashSet;
import java.util.Set;

public class GameTeam {

    private final int id;
    private final ChatColor color;
    private Set<GamePlayer> players = new HashSet<>();

    public GameTeam(int id, ChatColor color) {
        this.id = id;
        this.color = color;
    }

    public Set<GamePlayer> getPlayers() {
        return players;
    }

    public ChatColor getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    public void setPlayers(Set<GamePlayer> players) {
        this.players = players;
    }
}
