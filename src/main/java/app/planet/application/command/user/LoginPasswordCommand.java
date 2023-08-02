package app.planet.application.command.user;

public record LoginPasswordCommand(
        String email,
        String password
) {
}
