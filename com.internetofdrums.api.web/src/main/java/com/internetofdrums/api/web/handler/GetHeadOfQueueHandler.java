package com.internetofdrums.api.web.handler;

import com.internetofdrums.api.queue.service.api.DetailedDrumPattern;
import com.internetofdrums.api.queue.service.api.PatternService;
import com.internetofdrums.api.web.view.DetailedDrumPatternView;
import com.internetofdrums.api.web.view.ErrorView;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
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
    public void handle(HttpExchange httpExchange) throws IOException {
        LOGGER.fine("Handling get head of queue...");

        OutputStream outputStream = httpExchange.getResponseBody();

        String response;

        httpExchange
                .getResponseHeaders()
                .add("content-type", "application/json; charset=utf-8");

        Optional<DetailedDrumPattern> detailedDrumPattern = service.getHeadOfQueue();

        if (!detailedDrumPattern.isPresent()) {
            LOGGER.info("Queue is currently empty.");

            response = new ErrorView("The queue is currently empty.").toJson();

            httpExchange.sendResponseHeaders(404, response.length());

            outputStream.write(response.getBytes());
            outputStream.close();
            return;
        }

        LOGGER.fine("Got head of queue.");

        response = new DetailedDrumPatternView(detailedDrumPattern.get()).toJson();

        httpExchange.sendResponseHeaders(200, response.length());

        outputStream.write(response.getBytes());
        outputStream.close();

        LOGGER.fine("Get head of queue handled.");
    }
}
