package com.internetofdrums.api.web.handler;

import com.internetofdrums.api.queue.service.api.DetailedDrumPattern;
import com.internetofdrums.api.queue.service.api.PatternService;
import com.internetofdrums.api.web.view.DetailedDrumPatternView;
import com.internetofdrums.api.web.view.ErrorView;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.util.Optional;

public class GetPatternAndRemoveHeadOfQueueHandler extends HandlerForService<PatternService> {

    public static GetPatternAndRemoveHeadOfQueueHandler forService(PatternService service) {
        return new GetPatternAndRemoveHeadOfQueueHandler(service);
    }

    private GetPatternAndRemoveHeadOfQueueHandler(PatternService service) {
        super(service);
    }

    @Override
    public void handle(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();

        Optional<String> headOfQueuePattern = service.getPatternAndRemoveHeadOfQueue();

        if (!headOfQueuePattern.isPresent()) {
            response
                    .setStatusCode(404)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encode(new ErrorView("The queue is currently empty.")));

            return;
        }

        response
                .setStatusCode(200)
                .putHeader("content-type", "text/plain")
                .end(Json.encode(headOfQueuePattern.get()));
    }
}
