package com.internetofdrums.api.web;

import com.internetofdrums.api.queue.service.api.HealthService;
import com.internetofdrums.api.queue.service.api.PatternService;
import com.internetofdrums.api.web.handler.FailureHandler;
import com.internetofdrums.api.web.handler.GetAndRemoveHeadOfQueueHandler;
import com.internetofdrums.api.web.handler.GetHeadOfQueueHandler;
import com.internetofdrums.api.web.handler.GetHeadOfQueuePatternHandler;
import com.internetofdrums.api.web.handler.GetHealthHandler;
import com.internetofdrums.api.web.handler.GetPatternAndRemoveHeadOfQueueHandler;
import com.internetofdrums.api.web.handler.ListQueueHandler;
import com.internetofdrums.api.web.handler.OfferToQueueHandler;
import com.internetofdrums.api.web.handler.ResourceNotFoundHandler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

class WebService {

    private static final String API_VERSION = "/1.0";
    private static final Vertx vertx = Vertx.vertx();
    private static final Router router = Router.router(vertx);
    private static final Router subRouter = Router.router(vertx);

    private static final Logger LOGGER = Logger.getLogger(WebService.class.getName());

    private WebService() {
    }

    static void start(HealthService healthService, PatternService patternService) {
        LOGGER.fine("Starting web service...");

        configureCrossOriginResourceSharing();
        configureRouting(healthService, patternService);
        configureResourceNotFoundHandling();
        configureFailureHandling();
        startServer();

        LOGGER.fine("Web service started.");
    }

    private static void configureCrossOriginResourceSharing() {
        LOGGER.fine("Configuring cross origin resource sharing...");

        Set<String> allowedHeaders = new HashSet<>();
        allowedHeaders.add("x-requested-with");
        allowedHeaders.add("Access-Control-Allow-Origin");
        allowedHeaders.add("origin");
        allowedHeaders.add("Content-Type");
        allowedHeaders.add("accept");

        router
                .route()
                .handler(CorsHandler.create("*")
                        .allowedHeaders(allowedHeaders));

        LOGGER.fine("Cross origin resource sharing configured.");
    }

    private static void configureRouting(HealthService healthService, PatternService patternService) {
        LOGGER.fine("Configuring routing...");

        subRouter
                .route()
                .handler(BodyHandler.create());

        subRouter
                .get(HealthService.GET_HEALTH_PATH)
                .handler(GetHealthHandler.forService(healthService));

        subRouter
                .get(PatternService.LIST_QUEUE_PATH)
                .handler(ListQueueHandler.forService(patternService));

        subRouter
                .post(PatternService.OFFER_TO_QUEUE_PATH)
                .handler(OfferToQueueHandler.forService(patternService));

        subRouter
                .get(PatternService.GET_HEAD_OF_QUEUE_PATH)
                .handler(GetHeadOfQueueHandler.forService(patternService));

        subRouter
                .get(PatternService.GET_HEAD_OF_QUEUE_PATTERN_PATH)
                .handler(GetHeadOfQueuePatternHandler.forService(patternService));

        subRouter
                .delete(PatternService.GET_AND_REMOVE_HEAD_OF_QUEUE_PATH)
                .handler(GetAndRemoveHeadOfQueueHandler.forService(patternService));

        subRouter
                .delete(PatternService.GET_PATTERN_AND_REMOVE_HEAD_OF_QUEUE_PATH)
                .handler(GetPatternAndRemoveHeadOfQueueHandler.forService(patternService));

        LOGGER.fine("Routing configured.");
    }

    private static void configureResourceNotFoundHandling() {
        LOGGER.fine("Configuring resource not found handling...");

        router
                .route()
                .last()
                .handler(new ResourceNotFoundHandler());

        LOGGER.fine("Resource not found handling configured.");
    }

    private static void configureFailureHandling() {
        LOGGER.fine("Configuring failure handling...");

        router
                .route()
                .failureHandler(new FailureHandler());

        LOGGER.fine("Failure handling configured.");
    }

    private static void startServer() {
        LOGGER.fine("Starting server...");

        vertx
                .createHttpServer()
                .requestHandler(router.mountSubRouter(API_VERSION, subRouter)::accept)
                .listen(8080);

        LOGGER.fine("Server started.");
    }
}
