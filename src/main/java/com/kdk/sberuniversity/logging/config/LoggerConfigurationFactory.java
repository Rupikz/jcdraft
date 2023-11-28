package com.kdk.sberuniversity.logging.config;

import com.kdk.sberuniversity.logging.constants.Level;
import com.kdk.sberuniversity.logging.constants.Target;
import com.kdk.sberuniversity.logging.exceptions.LoggerConfigurationException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public final class LoggerConfigurationFactory {

    private final Map<String, LoggerConfiguration> configurations;

    public LoggerConfigurationFactory(Map<String, LoggerConfiguration> configurations) {
        this.configurations = configurations;
    }

    public static LoggerConfigurationFactory newInstance(String configPath) {
        try (InputStream is = LoggerConfigurationFactory.class.getClassLoader().getResourceAsStream(configPath)) {
            Unmarshaller unmarshaller = JAXBContext.newInstance(LoggerConfigurationSchema.class).createUnmarshaller();
            LoggerConfigurationSchema loggerConfigurationSchema = (LoggerConfigurationSchema) unmarshaller.unmarshal(is);

            Map<String, AppenderConfiguration> appenders = new HashMap<>();
            for (LoggerConfigurationSchema.Appender appender : loggerConfigurationSchema.getAppenders()) {
                appenders.put(appender.getName(), new AppenderConfiguration(
                        appender.getName(),
                        Target.valueOf(appender.getTarget().toUpperCase()),
                        appender.getPattern(),
                        appender.getFileName()));
            }

            Map<String, LoggerConfiguration> configurations = new HashMap<>();
            for (LoggerConfigurationSchema.Logger logger : loggerConfigurationSchema.getLoggers()) {
                configurations.put(logger.getName(), new LoggerConfiguration(
                        logger.getName(),
                        Level.valueOf(logger.getLevel()),
                        appenders.get(logger.getAppenderRef())));
            }

            return new LoggerConfigurationFactory(configurations);
        } catch (JAXBException ex) {
            throw new LoggerConfigurationException("Failed to parse logger configuration by path: " + configPath, ex);
        } catch (IOException ex) {
            throw new LoggerConfigurationException("Failed to read logger configuration by path: " + configPath, ex);
        }
    }


    public LoggerConfiguration get(String name) {
        if (name == null) {
            throw new NullPointerException("Logger configuration name cannot be null");
        }
        if (!configurations.containsKey(name)) {
            throw new LoggerConfigurationException("Logger configuration for logger name: '" + name + "' not found");
        }
        return configurations.get(name);
    }

}
