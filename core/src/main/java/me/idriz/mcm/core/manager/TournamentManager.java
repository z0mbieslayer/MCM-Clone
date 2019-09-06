package me.idriz.mcm.core.manager;

import me.idriz.mcm.core.Core;
import me.idriz.mcm.core.data.Tournament;
import me.idriz.mcm.core.payload.TournamentPayload;
import me.idriz.mcm.core.redis.Channels;
import me.idriz.mcm.core.redis.RedisManager;
import me.idriz.mcm.core.repository.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TournamentManager {

    private final Core plugin;

    private Optional<Tournament> selectedTournament;
    private final Repository<Integer, Tournament> tournamentRepository;
    private final Map<Integer, Tournament> tournaments = new HashMap<>();

    public TournamentManager(Core plugin, Repository<Integer, Tournament> tournamentRepository) {
        this.plugin = plugin;
        this.tournamentRepository = tournamentRepository;
        this.tournamentRepository.getRepositoryData().thenAcceptAsync(optional -> optional.ifPresent(tournaments::putAll));
    }

    /**
     * @return Memory store of tournaments.
     */
    public Map<Integer, Tournament> getTournaments() {
        return tournaments;
    }

    /**
     * @return the tournament repository provided to the manager.
     */
    public Repository<Integer, Tournament> getTournamentRepository() {
        return tournamentRepository;
    }

    /**
     * The selected tournament will act as a universal tournament that all games use.
     * This means all games will initially start with the selected tournament(usually the tournament is selected by a command like /select [id] in a hub server).
     * @return
     */
    public Optional<Tournament> getSelectedTournament() {
        return selectedTournament;
    }

    /**
     * @param selectedTournament The tournament selected.
     */
    public void setSelectedTournament(Tournament selectedTournament) {
        this.selectedTournament = Optional.ofNullable(selectedTournament);
        //TODO: Send redis payload to update.
        RedisManager.getInstance().getRedisBus().sendPayload(Channels.TOURNAMENT_SELECT, new TournamentPayload(selectedTournament));
    }

    public void save(Tournament tournament) {
        getTournamentRepository().save(tournament.getId(), tournament).thenRunAsync(() -> {
            //TODO: Send redis payload for saving.
            RedisManager.getInstance().getRedisBus().sendPayload(Channels.TOURNAMENT_UPDATE, new TournamentPayload(tournament));
        });
    }
}
