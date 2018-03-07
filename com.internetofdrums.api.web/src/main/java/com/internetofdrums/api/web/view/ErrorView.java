package com.internetofdrums.api.web.view;

import com.internetofdrums.api.queue.service.api.Error;

public class ErrorView implements Error {

    private final String message;

    public ErrorView(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
