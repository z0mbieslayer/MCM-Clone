package me.idriz.mcm.core;

import me.idriz.mcm.core.data.Tournament;
import me.idriz.mcm.core.manager.TournamentManager;
import me.idriz.mcm.core.repository.Repository;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    private static Core instance;
    private TournamentManager tournamentManager;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {

    }

    /**
     * Returns a singleton instance of the Core plugin.
     * @return The core instance.
     */
    public static Core getInstance() {
        return instance;
    }

    public TournamentManager getTournamentManager() {
        return tournamentManager;
    }
}
