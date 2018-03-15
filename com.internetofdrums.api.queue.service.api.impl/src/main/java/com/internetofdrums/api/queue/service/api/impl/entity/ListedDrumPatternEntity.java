package com.internetofdrums.api.queue.service.api.impl.entity;

import com.internetofdrums.api.queue.service.api.DetailedDrumPattern;
import com.internetofdrums.api.queue.service.api.ListedDrumPattern;

import java.util.UUID;

public class ListedDrumPatternEntity implements ListedDrumPattern {

    private final UUID id;
    private final String name;

    private ListedDrumPatternEntity(DetailedDrumPattern detailedDrumPattern) {
        this.id = detailedDrumPattern.getId();
        this.name = detailedDrumPattern.getName();
    }

    public static ListedDrumPatternEntity valueOf(DetailedDrumPattern detailedDrumPattern) {
        return new ListedDrumPatternEntity(detailedDrumPattern);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
