package com.kdk.sberuniversity.core.errors;

import com.kdk.sberuniversity.core.constants.ErrorConstants;

public final class IncorrectConfigError extends AppHostError {

    public IncorrectConfigError() {
        super(ErrorConstants.INCORRECT_CONFIG_ERROR);
    }

}
