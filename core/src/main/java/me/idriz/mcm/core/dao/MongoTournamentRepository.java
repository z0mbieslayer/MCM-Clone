package me.idriz.mcm.core.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.UpdateResult;
import me.idriz.mcm.core.Core;
import me.idriz.mcm.core.data.Tournament;
import me.idriz.mcm.core.repository.Repository;
import org.bson.Document;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class MongoTournamentRepository implements Repository<Integer, Tournament> {

    private final MongoCollection<Document> collection;
    private final ReplaceOptions upsertOption = new ReplaceOptions().upsert(true);

    public MongoTournamentRepository(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    @Override
    public CompletableFuture<Tournament> load(Integer key) {
        return CompletableFuture.supplyAsync(() -> {
            return null;
        });
    }

    @Override
    public CompletableFuture<Boolean> delete(Integer key) {
        return null;
    }

    @Override
    public CompletableFuture<Boolean> deleteByValue(Tournament value) {
        return null;
    }

    @Override
    public CompletableFuture<Boolean> save(Integer key, Tournament value) {
        return CompletableFuture.supplyAsync(() -> {
            if(value == null || key == null) return false;
            Document document = new Document("id", key)
                    .append("teams", Core.GSON.toJson(value.getTeams()))
                    .append("games", Core.GSON.toJson(value.getGames()));
            UpdateResult result = collection.replaceOne(Filters.eq("id", key), document, upsertOption);
            return result.wasAcknowledged();
        });
    }

    @Override
    public CompletableFuture<Optional<Map<Integer, Tournament>>> getRepositoryData() {
        return null;
    }
}
