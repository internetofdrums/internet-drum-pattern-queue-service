package com.internetofdrums.api.web.view;

import com.internetofdrums.api.queue.service.api.ListedDrumPattern;

import java.util.UUID;

public class ListedDrumPatternView extends View implements ListedDrumPattern {

    private final UUID id;
    private final String name;

    public ListedDrumPatternView(ListedDrumPattern listedDrumPattern) {
        this.id = listedDrumPattern.getId();
        this.name = listedDrumPattern.getName();
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
