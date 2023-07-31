package app.planet.domain.model.channel_mtm_planet;

import app.planet.domain.model.channel_mtm_user.ChannelMtmUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.OffsetDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.OffsetDateTime.now;

@Entity
public class ChanelMtmPlanet {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Long planetId;
    private Long channelId;
    private OffsetDateTime createTime;
    private OffsetDateTime updateTime;

    public ChanelMtmPlanet() {
    }

    public ChanelMtmPlanet(Long planetId, Long channelId) {
        this.planetId = planetId;
        this.channelId = channelId;
        this.createTime = now();
        this.updateTime = this.createTime;
    }

    public Long getId() {
        return id;
    }

    public Long getPlanetId() {
        return planetId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public OffsetDateTime getUpdateTime() {
        return updateTime;
    }
}
