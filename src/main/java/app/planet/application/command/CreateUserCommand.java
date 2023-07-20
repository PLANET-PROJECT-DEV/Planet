package app.planet.application.command;

public record CreateUserCommand(
        String email,
        String code
) {
}
