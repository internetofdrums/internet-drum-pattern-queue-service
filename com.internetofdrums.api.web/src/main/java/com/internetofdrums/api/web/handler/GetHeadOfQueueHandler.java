package com.internetofdrums.api.web.handler;

import com.internetofdrums.api.queue.service.api.DetailedDrumPattern;
import com.internetofdrums.api.queue.service.api.PatternService;
import com.internetofdrums.api.web.view.DetailedDrumPatternView;
import com.internetofdrums.api.web.view.ErrorView;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.util.Optional;
import java.util.logging.Logger;

public class GetHeadOfQueueHandler extends HandlerForService<PatternService> {

    private static final Logger LOGGER = Logger.getLogger(GetHeadOfQueueHandler.class.getName());

    public static GetHeadOfQueueHandler forService(PatternService service) {
        return new GetHeadOfQueueHandler(service);
    }

    private GetHeadOfQueueHandler(PatternService service) {
        super(service);

        LOGGER.fine("Created get head of queue handler.");
    }

    @Override
    public void handle(RoutingContext routingContext) {
        LOGGER.fine("Handling get head of queue...");

        HttpServerResponse response = routingContext.response();

        Optional<DetailedDrumPattern> detailedDrumPattern = service.getHeadOfQueue();

        if (!detailedDrumPattern.isPresent()) {
            LOGGER.info("Queue is currently empty.");

            response
                    .setStatusCode(404)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encode(new ErrorView("The queue is currently empty.")));

            return;
        }

        LOGGER.fine("Got head of queue.");

        response
                .setStatusCode(200)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encode(new DetailedDrumPatternView(detailedDrumPattern.get())));

        LOGGER.fine("Get head of queue handled.");
    }
}
