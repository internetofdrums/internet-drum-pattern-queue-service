package com.internetofdrums.api.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {

    private static final String FORMAT = "%1$s %2$-7s %3$s %4$s%n";

    @Override
    public String format(LogRecord logRecord) {
        String stackTrace = "";
        Throwable throwable = logRecord.getThrown();

        if (throwable != null) {
            StringWriter stringWriter = new StringWriter().append(System.getProperty("line.separator"));
            throwable.printStackTrace(new PrintWriter(stringWriter));

            stackTrace = stringWriter.toString();
        }

        return String.format(
                FORMAT,
                Instant.ofEpochMilli(logRecord.getMillis()).toString(),
                logRecord.getLevel().getName(),
                formatMessage(logRecord),
                stackTrace
        );
    }
}
