package com.kdk.sberuniversity.host.config;

import com.kdk.sberuniversity.core.errors.IncorrectConfigError;

import java.util.ResourceBundle;
import java.util.Set;

public final class ConfigService {

    private static volatile Config CONFIG;

    private ConfigService() {
    }

    public static Config getInstance() {
        if (CONFIG == null) {
            synchronized (Config.class) {
                if (CONFIG == null) {
                    CONFIG = new Config();
                    init();
                }
            }
        }
        return CONFIG;
    }

    private static void init() {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("JCDraft");
            Set<String> keys = rb.keySet();

            for (String keyElem : keys) {
                if (keyElem.equals("port")) {
                    CONFIG.setPort(Integer.valueOf(rb.getString(keyElem)));
                } else if (keyElem.equals("logger")) {
                    CONFIG.setLogger(rb.getString(keyElem));
                }
            }
        } catch (Exception ex) {
            throw new IncorrectConfigError();
        }
    }

}
