package com.internetofdrums.api.web.handler;

import com.internetofdrums.api.queue.service.api.DetailedDrumPattern;
import com.internetofdrums.api.queue.service.api.PatternService;
import com.internetofdrums.api.web.view.DetailedDrumPatternView;
import com.internetofdrums.api.web.view.ErrorView;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.util.Optional;

public class GetHeadOfQueueHandler extends HandlerForService<PatternService> {

    public static GetHeadOfQueueHandler forService(PatternService service) {
        return new GetHeadOfQueueHandler(service);
    }

    private GetHeadOfQueueHandler(PatternService service) {
        super(service);
    }

    @Override
    public void handle(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();

        Optional<DetailedDrumPattern> detailedDrumPattern = service.getHeadOfQueue();

        if (!detailedDrumPattern.isPresent()) {
            response
                    .setStatusCode(404)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encode(new ErrorView("The queue is currently empty.")));

            return;
        }

        response
                .setStatusCode(200)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encode(new DetailedDrumPatternView(detailedDrumPattern.get())));
    }
}
