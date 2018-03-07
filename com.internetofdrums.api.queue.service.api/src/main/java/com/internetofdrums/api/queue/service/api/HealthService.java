package com.internetofdrums.api.queue.service.api;

public interface HealthService extends Service {

    String BASE_PATH = "/health";
    String GET_HEALTH_PATH = BASE_PATH;

    void getHealth();
}
