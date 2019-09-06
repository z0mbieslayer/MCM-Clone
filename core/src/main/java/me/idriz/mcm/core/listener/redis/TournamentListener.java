package me.idriz.mcm.core.listener.redis;

import me.idriz.mcm.core.Core;
import me.idriz.mcm.core.payload.TournamentPayload;
import me.idriz.mcm.core.redis.Channels;
import me.idriz.mcm.core.redis.bus.annotation.MessageHandler;

public class TournamentListener {

    @MessageHandler(Channels.TOURNAMENT_SELECT)
    public void onSelect(TournamentPayload payload) {
        Core.getInstance().getTournamentManager().setSelectedTournament(payload.getTournament());
    }

    @MessageHandler(Channels.TOURNAMENT_UPDATE)
    public void onUpdate(TournamentPayload payload) {
        Core.getInstance().getTournamentManager().getTournaments().put(payload.getTournament().getId(), payload.getTournament());
    }
}
