package me.idriz.mcm.core.redis.bus.annotation;

import me.idriz.mcm.core.redis.bus.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MessageHandler {

    String value();

}
