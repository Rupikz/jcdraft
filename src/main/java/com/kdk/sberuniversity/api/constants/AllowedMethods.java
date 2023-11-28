package com.kdk.sberuniversity.api.constants;

import java.util.Arrays;

public enum AllowedMethods {

    LIST("list"),
    PAGE("page"),
    FIND_BY_CRITERIA("find"),
    SAVE("save"),
    DELETE("delete"),
    RESERVE("reserve"),
    CANCEL_RESERVE("cancel_reserve"),
    IS_RESERVED("is_reserved");

    private final String methodName;

    AllowedMethods(String methodName) {
        this.methodName = methodName;
    }

    public static AllowedMethods getEnum(String method) {
        return Arrays.stream(AllowedMethods.values())
                .filter(elem -> elem.methodName.equals(method))
                .findAny()
                .orElse(null);
    }

    public String getMethodName() {
        return methodName;
    }

}
