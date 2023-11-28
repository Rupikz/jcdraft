package com.kdk.sberuniversity.logging;

import com.kdk.sberuniversity.logging.config.LoggerConfiguration;
import com.kdk.sberuniversity.logging.constants.Level;
import com.kdk.sberuniversity.logging.constants.Target;
import com.kdk.sberuniversity.logging.exceptions.LoggingException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LoggerImpl implements Logger {

    public static final Pattern REGEX_DATETIME_PATTERN = Pattern.compile("\\{(.*?)}");

    private final String name;
    private final LoggerConfiguration configuration;

    LoggerImpl(final String name, LoggerConfiguration configuration) {
        this.name = name;
        this.configuration = configuration;
    }

    @Override
    public void log(Level level, String message) {
        if (level.ordinal() > configuration.minLogLevel().ordinal()) {
            return;
        }

        String modifiedMessage = getMessageByPattern(message, level);
        if (configuration.appender().target() == Target.CONSOLE) {
            printToConsole(modifiedMessage);
        } else if (configuration.appender().target() == Target.FILE) {
            printToFile(modifiedMessage);
        }
    }

    @Override
    public void trace(String message) {
        log(Level.TRACE, message);
    }

    @Override
    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    @Override
    public void info(String message) {
        log(Level.INFO, message);
    }

    @Override
    public void warn(String message) {
        log(Level.WARN, message);
    }

    @Override
    public void error(String message) {
        log(Level.ERROR, message);
    }

    @Override
    public void fatal(String message) {
        log(Level.FATAL, message);
    }

    public String getName() {
        return name;
    }

    private void printToConsole(String message) {
        System.out.println(message);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void printToFile(String message) {
        String fileName = getFileNameByPattern();
        File file = new File(fileName);

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException ex) {
                throw new LoggingException("Failed to create log file", ex);
            }
        }

        try (FileWriter fileWriter = new FileWriter(fileName, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(message);
        } catch (IOException ex) {
            throw new LoggingException("Failed to append new line to log file", ex);
        }
    }

    private String getFileNameByPattern() {
        String fileNamePattern = configuration.appender().fileName();

        Matcher matcher = REGEX_DATETIME_PATTERN.matcher(fileNamePattern);
        if (!matcher.find()) {
            return fileNamePattern;
        }

        String timePattern = matcher.group(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timePattern);
        String formattedDateTime = LocalDateTime.now().format(formatter);
        return fileNamePattern.replaceAll(REGEX_DATETIME_PATTERN.toString(), formattedDateTime);
    }

    private String getMessageByPattern(String message, Level level) {
        String msg = message.replaceAll("\\s+", " ");
        String pattern = configuration.appender().pattern();
        if (pattern == null) {
            return "[" + level.name() + "] [" + getName() + "] " + msg;
        }

        Matcher matcher = REGEX_DATETIME_PATTERN.matcher(pattern);
        if (matcher.find()) {
            String timePattern = matcher.group(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timePattern);
            String formattedDateTime = LocalDateTime.now().format(formatter);
            pattern = pattern.replaceAll(REGEX_DATETIME_PATTERN.toString(), formattedDateTime);
        }

        return pattern
                .replaceAll("level", level.name())
                .replaceAll("msg", msg);
    }

}
