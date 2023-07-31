package app.planet.domain.model.planet;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

import static app.planet.utils.Randoms.aRandomCoordinate;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.OffsetDateTime.now;

@Entity
public class Planet {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @SuppressWarnings("unused")
    private Long id;
    private Long masterId;
    private Integer users;
    private String icon;
    private String introduction;
    private String coordinate;
    private Type type;
    private Authentication authentication;
    private OffsetDateTime createTime;
    private OffsetDateTime updateTime;

    public Planet() {
    }

    public Planet(Long masterId) {
        this.masterId = masterId;
        this.users=0;
        this.icon = "icon.jpg";
        this.introduction = "It's a planet";
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
        return masterId;
    }

    public String getIcon() {
        return icon;
    }

    public String getIntroduction() {
        return introduction;
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
