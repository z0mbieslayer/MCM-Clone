package me.idriz.mcm.core.payload;

import me.idriz.mcm.core.data.Tournament;
import me.idriz.mcm.core.redis.bus.Payload;

public class TournamentPayload implements Payload {

    private final Tournament tournament;

    public TournamentPayload(Tournament tournament) {
        this.tournament = tournament;
    }

    public Tournament getTournament() {
        return tournament;
    }
}
