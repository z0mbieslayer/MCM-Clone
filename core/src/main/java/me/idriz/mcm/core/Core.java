package me.idriz.mcm.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.idriz.mcm.core.listener.redis.TournamentListener;
import me.idriz.mcm.core.manager.TournamentManager;
import me.idriz.mcm.core.redis.RedisManager;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.JedisPool;

public final class Core extends JavaPlugin {

    public static final Gson GSON = new GsonBuilder().create();

    private static Core instance;
    private TournamentManager tournamentManager;

    @Override
    public void onEnable() {
        instance = this;

        RedisManager.create(new JedisPool());
        RedisManager.setGson(GSON);

        registerListeners();
    }

    @Override
    public void onDisable() {

    }

    private void registerListeners() {

        RedisManager redisManager = RedisManager.getInstance();
        redisManager.getRedisBus().registerListener(new TournamentListener());

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
