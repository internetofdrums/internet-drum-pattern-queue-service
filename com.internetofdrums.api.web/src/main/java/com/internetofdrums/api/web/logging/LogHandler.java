package com.internetofdrums.api.web.logging;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

public class LogHandler extends StreamHandler {

    public LogHandler() {
        super(System.out, new LogFormatter());

        setLevel(Level.ALL);
    }

    @Override
    public synchronized void publish(LogRecord logRecord) {
        super.publish(logRecord);
        flush();
    }

    @Override
    public synchronized void close() throws SecurityException {
        flush();
    }
}
