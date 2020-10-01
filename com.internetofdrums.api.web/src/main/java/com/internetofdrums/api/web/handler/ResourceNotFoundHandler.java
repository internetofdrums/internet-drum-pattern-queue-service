package com.internetofdrums.api.web.handler;

import com.internetofdrums.api.web.view.ErrorView;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

public class ResourceNotFoundHandler implements HttpHandler {

    private static final Logger LOGGER = Logger.getLogger(ResourceNotFoundHandler.class.getName());

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        LOGGER.fine("Handling resource not found...");

        String response = new ErrorView("The resource could not be found.").toJson();

        httpExchange
                .getResponseHeaders()
                .add("content-type", "application/json; charset=utf-8");

        httpExchange.sendResponseHeaders(404, response.length());

        OutputStream outputStream = httpExchange.getResponseBody();

        outputStream.write(response.getBytes());
        outputStream.close();

        LOGGER.fine("Resource not found handled.");
    }
}
