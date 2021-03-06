package com.internetofdrums.api.web.handler;

import com.internetofdrums.api.queue.service.api.PatternService;
import com.internetofdrums.api.web.view.ListedDrumPatternView;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ListQueueHandler extends HandlerForService<PatternService> {

    private static final Logger LOGGER = Logger.getLogger(ListQueueHandler.class.getName());

    public static ListQueueHandler forService(PatternService service) {
        return new ListQueueHandler(service);
    }

    private ListQueueHandler(PatternService service) {
        super(service);

        LOGGER.fine("Created list queue handler.");
    }

    @Override
    public void handle(RoutingContext routingContext) {
        LOGGER.fine("Handling list queue...");

        HttpServerResponse response = routingContext.response();

        List<ListedDrumPatternView> list = service.listQueue()
                .stream()
                .map(ListedDrumPatternView::new)
                .collect(Collectors.toList());

        response
                .setStatusCode(200)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encode(list));

        LOGGER.fine("List queue handled.");
    }
}
