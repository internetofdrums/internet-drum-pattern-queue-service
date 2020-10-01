module com.internetofdrums.api.web {

    requires java.logging;
    requires com.internetofdrums.api.queue.service.api;
    requires jdk.httpserver;
    requires com.google.gson;

    uses com.internetofdrums.api.queue.service.api.HealthService;
    uses com.internetofdrums.api.queue.service.api.PatternService;

    opens com.internetofdrums.api.web.view to com.google.gson;
}
