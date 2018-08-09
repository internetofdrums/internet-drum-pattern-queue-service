package com.internetofdrums.api.web.handler;

import com.internetofdrums.api.queue.service.api.HealthService;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

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
    public void handle(RoutingContext routingContext) {
        LOGGER.fine("Handling get health...");

        HttpServerResponse response = routingContext.response();
        boolean serviceIsHealthy = true;

        try {
            service.getHealth();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Service is not healthy.", e);

            serviceIsHealthy = false;
        }

        if (!serviceIsHealthy) {
            response.setStatusCode(500).end();

            return;
        }

        response.setStatusCode(200).end();

        LOGGER.fine("Get health handled.");
    }
}
