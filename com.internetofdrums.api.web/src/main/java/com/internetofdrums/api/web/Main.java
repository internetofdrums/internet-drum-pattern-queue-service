package com.internetofdrums.api.web;

import com.google.gson.Gson;
import com.internetofdrums.api.queue.service.api.HealthService;
import com.internetofdrums.api.queue.service.api.PatternService;
import com.internetofdrums.api.web.logging.LogHandler;

import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    
    public static final Gson GSON = new Gson();

    public static void main(String[] args) {
        configureLogger();

        LOGGER.fine("Loading services...");

        HealthService healthService = ServiceLoader
                .load(HealthService.class)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Could not load health service."));
        PatternService patternService = ServiceLoader
                .load(PatternService.class)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Could not load pattern service."));

        LOGGER.fine("Services loaded.");

        WebService.start(healthService, patternService);
    }

    private static void configureLogger() {
        Logger logger = Logger.getLogger("com.internetofdrums");

        logger.addHandler(new LogHandler());
        logger.setLevel(Level.ALL);
        logger.setUseParentHandlers(false);
    }
}
