module com.internetofdrums.api.web {

    requires java.logging;
    requires vertx.web;
    requires vertx.core;
    requires jackson.annotations;
    requires com.internetofdrums.api.queue.service.api;

    uses com.internetofdrums.api.queue.service.api.HealthService;
    uses com.internetofdrums.api.queue.service.api.PatternService;

    opens com.internetofdrums.api.web.view to com.fasterxml.jackson.databind;
}
