package com.internetofdrums.api.web.handler;

import com.internetofdrums.api.web.view.ErrorView;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.util.logging.Logger;

public class FailureHandler implements Handler<RoutingContext> {

    private static final Logger LOGGER = Logger.getLogger(FailureHandler.class.getName());

    @Override
    public void handle(RoutingContext routingContext) {
        LOGGER.fine("Handling failure...");

        HttpServerResponse response = routingContext.response();

        response
                .setStatusCode(500)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encode(new ErrorView("An internal server error occured.")));

        LOGGER.fine("Failure handled.");
    }
}
