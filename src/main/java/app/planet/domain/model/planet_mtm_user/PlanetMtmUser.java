package app.planet.domain.model.planet_mtm_user;


import app.planet.domain.model.channel_mtm_user.ChannelMtmUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.OffsetDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.OffsetDateTime.now;

@Entity
public class PlanetMtmUser {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @SuppressWarnings("unused")
    private Long id;
    private Long userId;
    private Long planetId;
    private OffsetDateTime createTime;
    private OffsetDateTime updateTime;

    public PlanetMtmUser() {
    }

    public PlanetMtmUser(Long userId, Long planetId) {
        this.userId = userId;
        this.planetId = planetId;
        this.createTime = now();
        this.updateTime = this.createTime;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getPlanetId() {
        return planetId;
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public OffsetDateTime getUpdateTime() {
        return updateTime;
    }
}
