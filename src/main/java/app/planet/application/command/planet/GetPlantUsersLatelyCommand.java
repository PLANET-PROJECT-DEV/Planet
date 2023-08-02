package app.planet.application.command.planet;

public record GetPlantUsersLatelyCommand(
        Integer daysAgo
) {
}
