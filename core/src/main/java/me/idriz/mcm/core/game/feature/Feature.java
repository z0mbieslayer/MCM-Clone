package me.idriz.mcm.core.game.feature;

import java.util.function.Consumer;

public abstract class Feature {

    private boolean active = false;

    private Consumer<Feature> start, end;
    private Feature chained;

    public void onStart() {
        active = true;
    }

    public void onEnd() {
        active = false;
        end.accept(this);
        if (chained != null) chained.onStart();
    }

    public void shutdown() {
        active = false;
        if (chained != null) chained.shutdown();
    }

    public boolean isActive() {
        return active;
    }

    public Feature onStart(Consumer<Feature> consumer) {
        this.start = consumer;
        return this;
    }

    public Feature onEnd(Consumer<Feature> consumer) {
        this.end = consumer;
        return this;
    }

    public Feature chain(Feature next) {
        this.chained = next;
        return this;
    }


}
