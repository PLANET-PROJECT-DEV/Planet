package app.planet.application.command.user;

public record CreateUserCommand(
        String email,
        String code
) {
}
