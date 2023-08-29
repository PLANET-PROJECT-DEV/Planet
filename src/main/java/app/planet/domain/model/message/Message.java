package app.planet.domain.model.message;


import app.planet.application.command.chat.MessageCommand;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

import static java.time.OffsetDateTime.now;

@Entity
public class Message {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private Long from;
    private Long to;
    @Enumerated(EnumType.ORDINAL)
    private MessageType messageType;
    private String context;
    private OffsetDateTime createTime;
    private OffsetDateTime updateTime;

    public Message() {
    }

    public Message(MessageCommand messageCommand) {
        this.from = messageCommand.from();
        this.to = messageCommand.to();
        this.messageType = messageCommand.messageType();
        this.context = messageCommand.context();
        this.createTime = now();
        this.updateTime = this.createTime;
    }

    public enum MessageType {
        TEXT,VOICE,PICTURE
    }

    public Long getFrom() {
        return from;
    }

    public Long getTo() {
        return to;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getContext() {
        return context;
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public OffsetDateTime getUpdateTime() {
        return updateTime;
    }


    public Long getId() {
        return id;
    }

}
