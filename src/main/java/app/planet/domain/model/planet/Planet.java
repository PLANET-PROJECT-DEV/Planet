package app.planet.domain.model.planet;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

import static app.planet.utils.Randoms.aRandomCoordinate;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.OffsetDateTime.now;

public class Planet {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @SuppressWarnings("unused")
    private Long Id;
    private Long MasterId;
    private Long ChannelId;
    private String Icon;
    private String Introduction;
    private String coordinate;
    private Type type;
    private String authentication;
    private OffsetDateTime createTime;
    private OffsetDateTime updateTime;

    public Planet() {
    }

    public Planet(Long masterId, Long channelId, String authentication) {
        this.MasterId = masterId;
        this.ChannelId = channelId;
        this.Icon = "icon.jpg";
        this.Introduction = "It's a planet";
        this.coordinate = aRandomCoordinate();
        this.type = Type.OTHER;
        this.authentication = authentication;
        this.createTime = now();
        this.updateTime = this.createTime;
    }

    public enum Type{
        OTHER,GAME,MUSICIANS,MILITARY,SCIENCE
    }
    public Long getId() {
        return Id;
    }

    public Long getMasterId() {
        return MasterId;
    }

    public Long getChannelId() {
        return ChannelId;
    }

    public String getIcon() {
        return Icon;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public Type getType() {
        return type;
    }

    public String getAuthentication() {
        return authentication;
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public OffsetDateTime getUpdateTime() {
        return updateTime;
    }
}
