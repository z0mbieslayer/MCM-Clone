package me.idriz.mcm.core.repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Key paired repository interface. Lets you save by value, load by key.
 * @param <K> The key object to use when loading to get the value.
 * @param <V> The value object to return when loading.
 */
public interface Repository<K, V> {

    CompletableFuture<V> load(K key);

    CompletableFuture<Boolean> delete(K key);

    CompletableFuture<Boolean> deleteByValue(V value);

    CompletableFuture<Boolean> save(K key, V value);

    CompletableFuture<Optional<Map<K, V>>> getRepositoryData();

}
