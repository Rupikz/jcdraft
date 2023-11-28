package com.kdk.sberuniversity.logging;

import com.kdk.sberuniversity.logging.constants.Level;

public interface Logger {

    void log(Level level, String message);
    void trace(String message);
    void debug(String message);
    void info(String message);
    void warn(String message);
    void error(String message);
    void fatal(String message);

}
