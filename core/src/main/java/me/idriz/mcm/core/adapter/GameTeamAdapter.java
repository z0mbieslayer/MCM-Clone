package me.idriz.mcm.core.adapter;

import com.google.gson.*;
import me.idriz.mcm.core.data.game.GamePlayer;
import me.idriz.mcm.core.data.game.GameTeam;
import org.bukkit.ChatColor;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class GameTeamAdapter implements JsonSerializer<GameTeam>, JsonDeserializer<GameTeam> {

    public static JsonObject serialize(GameTeam team) {
        JsonObject object = new JsonObject();
        object.add("id", new JsonPrimitive(team.getId()));
        object.add("color", new JsonPrimitive(team.getColor().name()));

        JsonArray playerArray = new JsonArray();

        Set<GamePlayer> players = team.getPlayers();
        for(GamePlayer player : players) {
            JsonObject playerObject = new JsonObject();
            playerObject.add("uuid", new JsonPrimitive(player.getIdentifier().toString()));

            JsonArray scoreArray = new JsonArray();
            for(Map.Entry<Integer, Integer> score : player.getScore().entrySet()) {
                JsonObject scoreObject = new JsonObject();
                scoreObject.add("id", new JsonPrimitive(score.getKey()));
                scoreObject.add("score", new JsonPrimitive(score.getValue()));
            }

                playerObject.add("score", scoreArray);
        }

        object.add("players", playerArray);
        return object;
    }

    public static GameTeam deserialize(JsonObject object) {
        GameTeam team = new GameTeam(object.get("id").getAsInt(), ChatColor.valueOf(object.get("color").getAsString()));
        JsonArray playersArray = object.getAsJsonArray("players");
        playersArray.forEach(element -> {
            JsonObject playerObject = element.getAsJsonObject();
            GamePlayer player = new GamePlayer(UUID.fromString(playerObject.get("uuid").getAsString()));

            JsonArray scoreArray = playerObject.getAsJsonArray("score");
            scoreArray.forEach(scoreElement -> {
                JsonObject scoreObject = scoreElement.getAsJsonObject();
                player.addScore(scoreObject.get("id").getAsInt(), scoreObject.get("score").getAsInt());
            });

        });
        return team;
    }

    @Override
    public GameTeam deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return deserialize(json.getAsJsonObject());
    }

    @Override
    public JsonElement serialize(GameTeam src, Type typeOfSrc, JsonSerializationContext context) {
        return serialize(src);
    }
}
