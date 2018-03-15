package com.internetofdrums.api.web;

import com.internetofdrums.api.queue.service.api.HealthService;
import com.internetofdrums.api.queue.service.api.PatternService;

import java.util.ServiceLoader;

public class Main {

    public static void main(String[] args) {
        HealthService healthService = ServiceLoader
                .load(HealthService.class)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Could not load health service."));
        PatternService patternService = ServiceLoader
                .load(PatternService.class)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Could not load pattern service."));

        WebService.start(8080, healthService, patternService);
    }
}
