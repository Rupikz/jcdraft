package com.kdk.sberuniversity.logging.config;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Configuration", propOrder = {"appenders", "loggers"})
@XmlRootElement(name = "Configuration")
public final class LoggerConfigurationSchema {

    @XmlElementWrapper(name = "Appenders")
    @XmlElement(name = "Appender")
    private List<Appender> appenders;

    @XmlElementWrapper(name = "Loggers")
    @XmlElement(name = "Logger")
    private List<Logger> loggers;

    public LoggerConfigurationSchema(List<Appender> appenders, List<Logger> loggers) {
        this.appenders = appenders;
        this.loggers = loggers;
    }

    public LoggerConfigurationSchema() {
    }

    public List<Appender> getAppenders() {
        return appenders;
    }

    public void setAppenders(List<Appender> appenders) {
        this.appenders = appenders;
    }

    public List<Logger> getLoggers() {
        return loggers;
    }

    public void setLoggers(List<Logger> loggers) {
        this.loggers = loggers;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "Appender")
    @XmlRootElement(name = "Appender")
    public static class Appender {

        @XmlAttribute
        private String name;
        @XmlAttribute
        private String target;
        @XmlAttribute
        private String pattern;
        @XmlAttribute
        private String fileName;

        public Appender(String name, String target, String pattern, String fileName) {
            this.name = name;
            this.target = target;
            this.pattern = pattern;
            this.fileName = fileName;
        }

        public Appender() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public String getPattern() {
            return pattern;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "Logger")
    @XmlRootElement(name = "Logger")
    public static class Logger {

        @XmlAttribute
        private String name;
        @XmlAttribute
        private String level;
        @XmlAttribute
        private String appenderRef;

        public Logger(String name, String level, String appenderRef) {
            this.name = name;
            this.level = level;
            this.appenderRef = appenderRef;
        }

        public Logger() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getAppenderRef() {
            return appenderRef;
        }

        public void setAppenderRef(String appenderRef) {
            this.appenderRef = appenderRef;
        }

    }

}
