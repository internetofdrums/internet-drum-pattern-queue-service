package com.internetofdrums.api.web.view;

import com.internetofdrums.api.queue.service.api.DetailedDrumPattern;

import java.util.UUID;

public class DetailedDrumPatternView extends View implements DetailedDrumPattern {

    private final UUID id;
    private final String name;
    private final String pattern;

    public DetailedDrumPatternView(DetailedDrumPattern detailedDrumPattern) {
        this.id = detailedDrumPattern.getId();
        this.name = detailedDrumPattern.getName();
        this.pattern = detailedDrumPattern.getPattern();
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
