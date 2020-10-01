package com.internetofdrums.api.web.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.logging.Logger;

public class FailureHandler implements HttpHandler {

    private static final Logger LOGGER = Logger.getLogger(FailureHandler.class.getName());

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        LOGGER.fine("Handling failure...");

//        HttpServerResponse response = routingContext.response();
//
//        response
//                .setStatusCode(500)
//                .putHeader("content-type", "application/json; charset=utf-8")
//                .end(Json.encode(new ErrorView("An internal server error occured.")));

        LOGGER.fine("Failure handled.");
    }
}
