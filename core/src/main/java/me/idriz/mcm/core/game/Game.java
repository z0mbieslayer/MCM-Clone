package me.idriz.mcm.core.game;

import me.idriz.mcm.core.Core;
import me.idriz.mcm.core.data.Tournament;
import me.idriz.mcm.core.data.game.GameData;
import me.idriz.mcm.core.game.feature.Feature;
import me.idriz.mcm.core.manager.TournamentManager;

import java.util.HashMap;
import java.util.Map;

public abstract class Game {

    private final GameData gameData;
    private final Tournament tournament;

    private final TournamentManager manager;
    private boolean active;
    private Map<Class<? extends Feature>, Feature> features = new HashMap<>();

    public Game(GameData gameData, Tournament tournament) {
        this.manager = Core.getInstance().getTournamentManager();
        this.gameData = gameData;
        this.tournament = tournament;
    }

    public void onStart() {
        active = true;
    }

    public void onEnd() {
        active = false;
        shutdown();
    }

    public void shutdown() {
        manager.getSelectedTournament().ifPresent(manager::save);
    }

    public void forceShutdown() {
        features.values().forEach(Feature::shutdown);
        active = false;

    }

    public <T extends Feature> Game register(T t) {
        features.put(t.getClass(), t);
        return this;
    }

    public <T extends Feature> Game start(Class<T> clazz) {
        if(!active) {
            throw new IllegalStateException("The game hasn't started yet. You may not start a feature.");
        }
        get(clazz).onStart();
        return this;
    }

    public <T extends Feature> T get(Class<? extends Feature> clazz) {
        return (T) features.get(clazz);
    }

    public boolean isActive() {
        return active;
    }
}
