package app.planet.core.rest.exception;

public record ErrorResponse(
        String errorCode,
        String errorMessage
) {
}
