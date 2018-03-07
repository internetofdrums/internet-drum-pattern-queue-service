package com.internetofdrums.api.queue.service.api.impl;

import com.internetofdrums.api.queue.service.api.DetailedDrumPattern;
import com.internetofdrums.api.queue.service.api.NewDrumPattern;

import java.util.UUID;

public class DetailedDrumPatternEntity implements DetailedDrumPattern {

    private final UUID id;
    private final String name;
    private final String pattern;

    private DetailedDrumPatternEntity(UUID id, String name, String pattern) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null.");
        }

        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }

        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be null.");
        }

        if (pattern.length() != REQUIRED_PATTERN_LENGTH) {
            throw new IllegalArgumentException(String.format("Pattern must have length of %s.", REQUIRED_PATTERN_LENGTH));
        }

        this.id = id;
        this.name = name;
        this.pattern = pattern;
    }

    public static DetailedDrumPatternEntity valueOf(NewDrumPattern newDrumPattern) {
        return new DetailedDrumPatternEntity(UUID.randomUUID(), newDrumPattern.getName(), newDrumPattern.getPattern());
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPattern() {
        return pattern;
    }
}
