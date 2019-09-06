package me.idriz.mcm.core;

import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    private static Core instance;

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
}
