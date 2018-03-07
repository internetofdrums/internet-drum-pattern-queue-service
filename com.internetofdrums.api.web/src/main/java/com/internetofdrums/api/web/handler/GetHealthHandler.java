package com.internetofdrums.api.web.handler;

import com.internetofdrums.api.queue.service.api.HealthService;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

public class GetHealthHandler extends HandlerForService<HealthService> {

    public static GetHealthHandler forService(HealthService service) {
        return new GetHealthHandler(service);
    }

    private GetHealthHandler(HealthService service) {
        super(service);
    }

    @Override
    public void handle(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        boolean serviceIsHealthy = true;

        try {
            service.getHealth();
        } catch (Exception e) {
            serviceIsHealthy = false;
        }

        if (!serviceIsHealthy) {
            response.setStatusCode(500).end();

            return;
        }

        response.setStatusCode(200).end();
    }
}
