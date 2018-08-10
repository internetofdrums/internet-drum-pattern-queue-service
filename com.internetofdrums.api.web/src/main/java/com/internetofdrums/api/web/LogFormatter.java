package com.internetofdrums.api.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class LogFormatter extends SimpleFormatter {

    private static final String FORMAT = "%1$s %4$-7s %2$s - %5$s %6$s%n";

    private static String getDateTime(LogRecord logRecord) {
        return Instant.ofEpochMilli(logRecord.getMillis()).toString();
    }

    private static String getSource(LogRecord logRecord) {
        if (logRecord.getSourceClassName() == null) {
            return logRecord.getLoggerName();
        }

        String source = logRecord.getSourceClassName();

        if (logRecord.getSourceMethodName() != null) {
            source += " " + logRecord.getSourceMethodName();
        }

        return source;
    }

    private static String getThrowable(LogRecord logRecord) {
        if (logRecord.getThrown() == null) {
            return "";
        }

        StringWriter stringWriter = new StringWriter().append(System.getProperty("line.separator"));
        PrintWriter printWriter = new PrintWriter(stringWriter);

        logRecord.getThrown().printStackTrace(printWriter);
        printWriter.close();

        return stringWriter.toString();
    }

    @Override
    public String format(LogRecord logRecord) {
        return String.format(
                FORMAT,
                getDateTime(logRecord),
                getSource(logRecord),
                logRecord.getLoggerName(),
                logRecord.getLevel().getName(),
                formatMessage(logRecord),
                getThrowable(logRecord)
        );
    }
}
