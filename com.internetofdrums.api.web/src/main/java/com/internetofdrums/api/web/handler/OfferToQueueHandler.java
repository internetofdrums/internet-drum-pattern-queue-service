package com.internetofdrums.api.web.handler;

import com.internetofdrums.api.queue.service.api.PatternService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.logging.Logger;

public class OfferToQueueHandler extends HandlerForService<PatternService> {

    private static final Logger LOGGER = Logger.getLogger(OfferToQueueHandler.class.getName());

    public static OfferToQueueHandler forService(PatternService service) {
        return new OfferToQueueHandler(service);
    }

    private OfferToQueueHandler(PatternService service) {
        super(service);

        LOGGER.fine("Created offer to queue handler.");
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        LOGGER.fine("Handling offer to queue...");

//        HttpServerResponse response = routingContext.response();
//        NewDrumPattern newDrumPattern;
//
//        LOGGER.fine("Decoding drum pattern...");
//
//        try {
//            newDrumPattern = Json.decodeValue(routingContext.getBodyAsString(), NewDrumPatternView.class);
//        } catch (DecodeException e) {
//            LOGGER.log(Level.SEVERE, "Exception occurred while decoding drum pattern.", e);
//
//            response
//                    .setStatusCode(400)
//                    .putHeader("content-type", "application/json; charset=utf-8")
//                    .end(Json.encode(new ErrorView("The pattern could not be correctly parsed.")));
//
//            return;
//        }
//
//        boolean succeeded;
//
//        LOGGER.fine("Offering drum pattern to queue...");
//
//        try {
//            succeeded = service.offerToQueue(newDrumPattern);
//        } catch (QueueException e) {
//            LOGGER.info("Pattern is already present in queue...");
//
//            response
//                    .setStatusCode(422)
//                    .putHeader("content-type", "application/json; charset=utf-8")
//                    .end(Json.encode(new ErrorView("The pattern is already present in the queue.")));
//
//            return;
//        }
//
//        if (!succeeded) {
//            LOGGER.info("Pattern could not be added to queue...");
//
//            response
//                    .setStatusCode(202)
//                    .putHeader("content-type", "application/json; charset=utf-8")
//                    .end(Json.encode(new ErrorView("The pattern could not be added to the queue, because the queue is currently full.")));
//
//            return;
//        }
//
//        LOGGER.fine("Pattern successfully added to queue...");
//
//        response
//                .setStatusCode(201)
//                .end();

        LOGGER.fine("Offer to queue handled.");
    }
}
