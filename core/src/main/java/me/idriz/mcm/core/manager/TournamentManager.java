package me.idriz.mcm.core.manager;

import me.idriz.mcm.core.Core;
import me.idriz.mcm.core.data.Tournament;
import me.idriz.mcm.core.repository.Repository;

import java.util.HashMap;
import java.util.Map;

public class TournamentManager {

    private final Core plugin;

    private final Repository<Integer, Tournament> tournamentRepository;
    private final Map<Integer, Tournament> tournaments = new HashMap<>();

    public TournamentManager(Core plugin, Repository<Integer, Tournament> tournamentRepository) {
        this.plugin = plugin;
        this.tournamentRepository = tournamentRepository;
        this.tournamentRepository.getRepositoryData().thenAcceptAsync(optional -> optional.ifPresent(tournaments::putAll));
    }

    /**
     *
     * @return the tournament repository provided to the manager.
     */
    public Repository<Integer, Tournament> getTournamentRepository() {
        return tournamentRepository;
    }
}
