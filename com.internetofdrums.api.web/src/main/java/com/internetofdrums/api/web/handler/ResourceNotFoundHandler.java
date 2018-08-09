package com.internetofdrums.api.web.handler;

import com.internetofdrums.api.web.view.ErrorView;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.util.logging.Logger;

public class ResourceNotFoundHandler implements Handler<RoutingContext> {

    private static final Logger LOGGER = Logger.getLogger(ResourceNotFoundHandler.class.getName());

    @Override
    public void handle(RoutingContext routingContext) {
        LOGGER.fine("Handling resource not found...");
        
        HttpServerResponse response = routingContext.response();

        response
                .setStatusCode(404)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encode(new ErrorView("The resource could not be found.")));

        LOGGER.fine("Resource not found handled.");
    }
}
