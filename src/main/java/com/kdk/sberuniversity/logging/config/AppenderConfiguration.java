package com.kdk.sberuniversity.logging.config;

import com.kdk.sberuniversity.logging.constants.Target;

public record AppenderConfiguration(String name, Target target,
                                    String pattern, String fileName) {
}
