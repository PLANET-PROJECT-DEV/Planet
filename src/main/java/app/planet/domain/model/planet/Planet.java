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
    private Long id;
    private Long MasterId;
    private Long ChannelId;
    private Integer users;
    private String Icon;
    private String Introduction;
    private String coordinate;
    private Type type;
    private Authentication authentication;
    private OffsetDateTime createTime;
    private OffsetDateTime updateTime;

    public Planet() {
    }

    public Planet(Long masterId, Long channelId) {
        this.MasterId = masterId;
        this.ChannelId = channelId;
        this.users=0;
        this.Icon = "icon.jpg";
        this.Introduction = "It's a planet";
        this.coordinate = aRandomCoordinate();
        this.type = Type.OTHER;
        this.authentication = Authentication.DEFAULT;
        this.createTime = now();
        this.updateTime = this.createTime;
    }

    public enum Type{
        OTHER,GAME,MUSICIANS,MILITARY,SCIENCE
    }

    public enum Authentication{
        DEFAULT,PERSONAGE,FIRM
    }

    public Long getId() {
        return id;
    }

    public Integer getUsers() {
        return users;
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

    public Authentication getAuthentication() {
        return authentication;
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public OffsetDateTime getUpdateTime() {
        return updateTime;
    }
}
