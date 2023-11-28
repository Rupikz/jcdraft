package com.kdk.sberuniversity.logging;

import com.kdk.sberuniversity.host.config.Config;
import com.kdk.sberuniversity.host.config.ConfigService;
import com.kdk.sberuniversity.logging.config.LoggerConfigurationFactory;

import java.util.HashMap;
import java.util.Map;

public final class LogManager {

    private static final Map<String, Logger> loggerRegistry = new HashMap<>();
    private static final LoggerConfigurationFactory configurationFactory;

    static {
        Config config = ConfigService.getInstance();
        configurationFactory = LoggerConfigurationFactory.newInstance(config.getLogger());
    }

    static Logger getLogger(String name) {
        if (name == null) {
            throw new NullPointerException("Logger name cannot be null");
        }
        loggerRegistry.putIfAbsent(name, new LoggerImpl(name, configurationFactory.get(name)));
        return loggerRegistry.get(name);
    }

}
