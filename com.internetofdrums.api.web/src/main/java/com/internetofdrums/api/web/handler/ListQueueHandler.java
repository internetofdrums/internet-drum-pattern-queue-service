package com.internetofdrums.api.web.handler;

import com.internetofdrums.api.queue.service.api.PatternService;
import com.internetofdrums.api.web.view.ListedDrumPatternView;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
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
    public void handle(HttpExchange httpExchange) throws IOException {
        LOGGER.fine("Handling list queue...");

        OutputStream outputStream = httpExchange.getResponseBody();

        List<ListedDrumPatternView> list = service.listQueue()
                .stream()
                .map(ListedDrumPatternView::new)
                .collect(Collectors.toList());

        String response = list.toString();

        httpExchange
                .getResponseHeaders()
                .add("content-type", "application/json; charset=utf-8");

        httpExchange.sendResponseHeaders(200, response.length());

        outputStream.write(response.getBytes());
        outputStream.close();

        LOGGER.fine("List queue handled.");
    }
}
