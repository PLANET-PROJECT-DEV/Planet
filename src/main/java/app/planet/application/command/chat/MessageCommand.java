package app.planet.application.command.chat;

import app.planet.domain.model.message.Message.MessageType;

public record MessageCommand(
        String context,
        Long from,
        Long to,
        MessageType messageType
) {
}
