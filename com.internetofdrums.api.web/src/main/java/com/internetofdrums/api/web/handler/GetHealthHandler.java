package com.internetofdrums.api.web.handler;

import com.internetofdrums.api.queue.service.api.HealthService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetHealthHandler extends HandlerForService<HealthService> {

    private static final Logger LOGGER = Logger.getLogger(GetHealthHandler.class.getName());

    public static GetHealthHandler forService(HealthService service) {
        return new GetHealthHandler(service);
    }

    private GetHealthHandler(HealthService service) {
        super(service);

        LOGGER.fine("Created get health handler.");
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        LOGGER.fine("Handling get health...");

        boolean serviceIsHealthy = true;

        try {
            service.getHealth();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Service is not healthy.", e);

            serviceIsHealthy = false;
        }

        if (!serviceIsHealthy) {
            httpExchange.sendResponseHeaders(500, -1);
            return;
        }

        httpExchange.sendResponseHeaders(200, -1);

        LOGGER.fine("Get health handled.");
    }
}
