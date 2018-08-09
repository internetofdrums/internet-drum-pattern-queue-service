package com.internetofdrums.api.web.view;

import com.internetofdrums.api.queue.service.api.NewDrumPattern;

public class NewDrumPatternView implements NewDrumPattern {

    private final String name;
    private final String pattern;

    public NewDrumPatternView(String name, String pattern) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        if (pattern == null || pattern.isEmpty()) {
            throw new IllegalArgumentException("Pattern cannot be null or empty.");
        }

        this.name = name;
        this.pattern = pattern;
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
