package app.planet.application.command;

public record LoginPasswordCommand(
        String email,
        String password
) {
}
