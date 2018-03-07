module com.internetofdrums.api.web {

    requires vertx.web;
    requires vertx.core;
    requires com.fasterxml.jackson.annotation;
    requires com.internetofdrums.api.queue.service.api;

    uses io.vertx.core.Vertx;
    uses com.internetofdrums.api.queue.service.api.HealthService;
    uses com.internetofdrums.api.queue.service.api.PatternService;

    opens com.internetofdrums.api.web.view to com.fasterxml.jackson.databind;
}
