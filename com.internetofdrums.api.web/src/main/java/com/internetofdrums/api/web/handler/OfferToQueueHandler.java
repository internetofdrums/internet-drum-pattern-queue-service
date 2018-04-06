package com.internetofdrums.api.web.handler;

import com.internetofdrums.api.queue.service.api.NewDrumPattern;
import com.internetofdrums.api.queue.service.api.PatternService;
import com.internetofdrums.api.queue.service.api.QueueException;
import com.internetofdrums.api.web.view.ErrorView;
import com.internetofdrums.api.web.view.NewDrumPatternView;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

public class OfferToQueueHandler extends HandlerForService<PatternService> {

    public static OfferToQueueHandler forService(PatternService service) {
        return new OfferToQueueHandler(service);
    }

    private OfferToQueueHandler(PatternService service) {
        super(service);
    }

    @Override
    public void handle(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        NewDrumPattern newDrumPattern;

        try {
            newDrumPattern = Json.decodeValue(routingContext.getBodyAsString(), NewDrumPatternView.class);
        } catch (DecodeException e) {
            response
                    .setStatusCode(400)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encode(new ErrorView("The pattern could not be correctly parsed.")));

            return;
        }

        boolean succeeded;

        try {
            succeeded = service.offerToQueue(newDrumPattern);
        } catch (QueueException e) {
            response
                    .setStatusCode(422)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encode(new ErrorView("The pattern is already present in the queue.")));

            return;
        }

        if (!succeeded) {
            response
                    .setStatusCode(202)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encode(new ErrorView("The pattern could not be added to the queue, because the queue is currently full.")));

            return;
        }

        response
                .setStatusCode(201)
                .end();
    }
}
