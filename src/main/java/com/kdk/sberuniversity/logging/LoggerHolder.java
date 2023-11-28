package com.kdk.sberuniversity.logging;

public final class LoggerHolder {

    public static final Logger rootLogger = LogManager.getLogger("rootLogger");
    public static final Logger auditLogger = LogManager.getLogger("auditLogger");
    public static final Logger apiLogger = LogManager.getLogger("apiLogger");

}
