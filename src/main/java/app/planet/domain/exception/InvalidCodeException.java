package app.planet.domain.exception;


import app.planet.core.exception.BusinessException;

import static app.planet.core.constant.ExceptionConstant.ExceptionCodes.INVALID_LOGIN_CODE_EXCEPTION;

public class InvalidCodeException extends BusinessException {
    public InvalidCodeException() {
        super(INVALID_LOGIN_CODE_EXCEPTION);
    }
}
