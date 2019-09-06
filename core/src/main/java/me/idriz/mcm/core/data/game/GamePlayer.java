package me.idriz.mcm.core.data.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class GamePlayer {

    //The key in this case is the GameData identifier, the value being the score garnered by the player.
    private final Map<Integer, Integer> score = new HashMap<>();
    private final UUID identifier;

    private boolean alive = true;

    public GamePlayer(UUID identifier) {
        this.identifier = identifier;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public Map<Integer, Integer> getScore() {
        return score;
    }

    public void addScore(int game, int n) {
        score.put(game, score.getOrDefault(game, 0) + n);
    }

    public void subtractScore(int game, int n) {
        score.put(game, score.get(game) == null ? 0 : score.get(game) - n);
    }

    public void setScore(int game, int score) {
        this.score.put(game, score);
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public Optional<Player> getPlayer() {
        return Optional.ofNullable(Bukkit.getPlayer(identifier));
    }
}
