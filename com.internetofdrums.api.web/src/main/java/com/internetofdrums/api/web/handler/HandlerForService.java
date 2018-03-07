package com.internetofdrums.api.web.handler;

import com.internetofdrums.api.queue.service.api.Service;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

abstract class HandlerForService<T extends Service> implements Handler<RoutingContext> {

    final T service;

    HandlerForService(T service) {
        this.service = service;
    }
}
