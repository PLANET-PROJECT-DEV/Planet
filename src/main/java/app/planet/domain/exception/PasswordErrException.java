package app.planet.domain.exception;


import app.planet.core.exception.BusinessException;

import static app.planet.core.constant.ExceptionConstant.ExceptionCodes.PASSWORD_ERR_EXCEPTION;

public class PasswordErrException extends BusinessException {

    public PasswordErrException() {
        super(PASSWORD_ERR_EXCEPTION);
    }
}
