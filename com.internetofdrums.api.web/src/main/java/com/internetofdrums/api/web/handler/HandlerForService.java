package com.internetofdrums.api.web.handler;

import com.internetofdrums.api.queue.service.api.Service;
import com.sun.net.httpserver.HttpHandler;

abstract class HandlerForService<T extends Service> implements HttpHandler {

    final T service;

    HandlerForService(T service) {
        this.service = service;
    }
}
