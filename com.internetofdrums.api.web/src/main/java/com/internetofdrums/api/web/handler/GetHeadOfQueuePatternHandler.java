package com.internetofdrums.api.web.handler;

import com.internetofdrums.api.queue.service.api.PatternService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.logging.Logger;

public class GetHeadOfQueuePatternHandler extends HandlerForService<PatternService> {

    private static final Logger LOGGER = Logger.getLogger(GetHeadOfQueuePatternHandler.class.getName());

    public static GetHeadOfQueuePatternHandler forService(PatternService service) {
        return new GetHeadOfQueuePatternHandler(service);
    }

    private GetHeadOfQueuePatternHandler(PatternService service) {
        super(service);

        LOGGER.fine("Created get head of queue pattern handler.");
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        LOGGER.fine("Handling get head of queue pattern...");

//        HttpServerResponse response = routingContext.response();
//
//        Optional<String> headOfQueuePattern = service.getHeadOfQueuePattern();
//
//        if (!headOfQueuePattern.isPresent()) {
//            LOGGER.info("Queue is currently empty.");
//
//            response
//                    .setStatusCode(404)
//                    .putHeader("content-type", "application/json; charset=utf-8")
//                    .end(Json.encode(new ErrorView("The queue is currently empty.")));
//
//            return;
//        }
//
//        response
//                .setStatusCode(200)
//                .putHeader("content-type", "text/plain")
//                .end(headOfQueuePattern.get());

        LOGGER.fine("Get head of queue pattern handled.");
    }
}
