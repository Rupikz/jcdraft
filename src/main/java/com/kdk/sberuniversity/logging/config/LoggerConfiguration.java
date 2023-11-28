package com.kdk.sberuniversity.logging.config;

import com.kdk.sberuniversity.logging.constants.Level;

public record LoggerConfiguration(String name, Level minLogLevel,
                                  AppenderConfiguration appender) {
}

