package com.internetofdrums.api.web.handler;

import com.internetofdrums.api.queue.service.api.PatternService;
import com.internetofdrums.api.web.view.ErrorView;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.util.Optional;
import java.util.logging.Logger;

public class GetPatternAndRemoveHeadOfQueueHandler extends HandlerForService<PatternService> {

    private static final Logger LOGGER = Logger.getLogger(GetPatternAndRemoveHeadOfQueueHandler.class.getName());

    public static GetPatternAndRemoveHeadOfQueueHandler forService(PatternService service) {
        return new GetPatternAndRemoveHeadOfQueueHandler(service);
    }

    private GetPatternAndRemoveHeadOfQueueHandler(PatternService service) {
        super(service);

        LOGGER.fine("Created get pattern and remove head of queue handler.");
    }

    @Override
    public void handle(RoutingContext routingContext) {
        LOGGER.fine("Handling get pattern and remove head of queue...");

        HttpServerResponse response = routingContext.response();

        Optional<String> headOfQueuePattern = service.getPatternAndRemoveHeadOfQueue();

        if (!headOfQueuePattern.isPresent()) {
            LOGGER.info("Queue is currently empty.");

            response
                    .setStatusCode(404)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encode(new ErrorView("The queue is currently empty.")));

            return;
        }

        LOGGER.fine("Got pattern and removed head of queue.");

        response
                .setStatusCode(200)
                .putHeader("content-type", "text/plain")
                .end(headOfQueuePattern.get());

        LOGGER.fine("Get pattern and remove head of queue handled.");
    }
}
