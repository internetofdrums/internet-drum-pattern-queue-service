package com.internetofdrums.api.web.handler;

import com.internetofdrums.api.queue.service.api.DetailedDrumPattern;
import com.internetofdrums.api.queue.service.api.PatternService;
import com.internetofdrums.api.web.view.DetailedDrumPatternView;
import com.internetofdrums.api.web.view.ErrorView;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.util.Optional;

public class GetAndRemoveHeadOfQueueHandler extends HandlerForService<PatternService> {

    public static GetAndRemoveHeadOfQueueHandler forService(PatternService service) {
        return new GetAndRemoveHeadOfQueueHandler(service);
    }

    private GetAndRemoveHeadOfQueueHandler(PatternService service) {
        super(service);
    }

    @Override
    public void handle(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();

        Optional<DetailedDrumPattern> detailedDrumPattern = service.getAndRemoveHeadOfQueue();

        if (!detailedDrumPattern.isPresent()) {
            response
                    .setStatusCode(404)
                    .end(Json.encode(new ErrorView("The queue is currently empty.")));

            return;
        }

        response
                .setStatusCode(200)
                .end(Json.encode(new DetailedDrumPatternView(detailedDrumPattern.get())));
    }
}
