package com.internetofdrums.api.web.handler;

import com.internetofdrums.api.web.view.ErrorView;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

public class FailureHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();

        response
                .setStatusCode(404)
                .end(Json.encode(new ErrorView("The resource could not be found.")));
    }
}
