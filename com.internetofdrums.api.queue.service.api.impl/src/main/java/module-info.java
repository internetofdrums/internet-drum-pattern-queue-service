module com.internetofdrums.api.queue.service.api.impl {

    requires com.internetofdrums.api.queue.service.api;

    provides com.internetofdrums.api.queue.service.api.HealthService with com.internetofdrums.api.queue.service.api.impl.HealthServiceImpl;
    provides com.internetofdrums.api.queue.service.api.PatternService with com.internetofdrums.api.queue.service.api.impl.PatternServiceImpl;
}
