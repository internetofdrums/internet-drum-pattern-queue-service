package com.internetofdrums.api.web;

import com.internetofdrums.api.queue.service.api.HealthService;
import com.internetofdrums.api.queue.service.api.PatternService;
import com.internetofdrums.api.web.handler.GetHeadOfQueueHandler;
import com.internetofdrums.api.web.handler.GetHealthHandler;
import com.internetofdrums.api.web.handler.ListQueueHandler;
import com.internetofdrums.api.web.handler.OfferToQueueHandler;
import com.internetofdrums.api.web.handler.ResourceNotFoundHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

class WebService {

    private static final String API_VERSION = "/1.0";
    private static final HttpServer httpServer;
    private static final Logger LOGGER = Logger.getLogger(WebService.class.getName());

    static {
        try {
            httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private WebService() {
    }

    static void start(HealthService healthService, PatternService patternService) {
        LOGGER.fine("Starting web service...");

//        configureCrossOriginResourceSharing();
        configureRouting(healthService, patternService);
        configureResourceNotFoundHandling();
//        configureFailureHandling();
        startServer();

        LOGGER.fine("Web service started.");
    }

    //    private static void configureCrossOriginResourceSharing() {
//        LOGGER.fine("Configuring cross origin resource sharing...");
//
//        Set<String> allowedHeaders = new HashSet<>();
//        allowedHeaders.add("x-requested-with");
//        allowedHeaders.add("Access-Control-Allow-Origin");
//        allowedHeaders.add("origin");
//        allowedHeaders.add("Content-Type");
//        allowedHeaders.add("accept");
//
//        router
//                .route()
//                .handler(CorsHandler.create("*")
//                        .allowedHeaders(allowedHeaders));
//
//        LOGGER.fine("Cross origin resource sharing configured.");
//    }
//
    private static void configureRouting(HealthService healthService, PatternService patternService) {
        LOGGER.fine("Configuring routing...");

//        subRouter
//                .route()
//                .handler(BodyHandler.create());

        httpServer
                .createContext(API_VERSION + HealthService.GET_HEALTH_PATH)
                .setHandler(GetHealthHandler.forService(healthService));

        httpServer
                .createContext(API_VERSION + PatternService.LIST_QUEUE_PATH)
                .setHandler(ListQueueHandler.forService(patternService));

        httpServer
                .createContext(API_VERSION + PatternService.OFFER_TO_QUEUE_PATH)
                .setHandler(OfferToQueueHandler.forService(patternService));

        httpServer
                .createContext(API_VERSION + PatternService.GET_HEAD_OF_QUEUE_PATH)
                .setHandler(GetHeadOfQueueHandler.forService(patternService));

//        subRouter
//                .get(PatternService.GET_HEAD_OF_QUEUE_PATTERN_PATH)
//                .handler(GetHeadOfQueuePatternHandler.forService(patternService));

//        subRouter
//                .delete(PatternService.GET_AND_REMOVE_HEAD_OF_QUEUE_PATH)
//                .handler(GetAndRemoveHeadOfQueueHandler.forService(patternService));

//        subRouter
//                .delete(PatternService.GET_PATTERN_AND_REMOVE_HEAD_OF_QUEUE_PATH)
//                .handler(GetPatternAndRemoveHeadOfQueueHandler.forService(patternService));

        LOGGER.fine("Routing configured.");
    }

    private static void configureResourceNotFoundHandling() {
        LOGGER.fine("Configuring resource not found handling...");

        httpServer
                .createContext("/")
                .setHandler(new ResourceNotFoundHandler());

        LOGGER.fine("Resource not found handling configured.");
    }

    //
    private static void configureFailureHandling() {
        LOGGER.fine("Configuring failure handling...");

//        router
//                .route()
//                .failureHandler(new FailureHandler());
//
        LOGGER.fine("Failure handling configured.");
    }

    private static void startServer() {
        LOGGER.fine("Starting server...");

        httpServer.start();

//        vertx
//                .createHttpServer()
//                .requestHandler(router.mountSubRouter(API_VERSION, subRouter)::accept)
//                .listen(8080);

        LOGGER.fine("Server started.");
    }
}
