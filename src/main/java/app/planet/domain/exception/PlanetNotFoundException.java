package app.planet.domain.exception;

import app.planet.core.exception.BusinessException;

import static app.planet.core.constant.ExceptionConstant.ExceptionCodes.PLANET_NOTFOUND_EXCEPTION;

public class PlanetNotFoundException extends BusinessException {
    public PlanetNotFoundException() {
        super(PLANET_NOTFOUND_EXCEPTION);
    }
}
