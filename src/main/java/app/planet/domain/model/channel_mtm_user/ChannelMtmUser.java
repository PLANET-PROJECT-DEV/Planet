package app.planet.domain.model.channel_mtm_user;

import app.planet.domain.model.channel.Channel;
import app.planet.domain.model.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.Table;

import java.time.OffsetDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.OffsetDateTime.now;

@Entity
@Table(appliesTo="channel_mtm_user")
public class ChannelMtmUser {
    @Id
    @Column(name = "cmu_id",nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;
    private OffsetDateTime createTime;
    private OffsetDateTime updateTime;

    public ChannelMtmUser() {
    }

    public ChannelMtmUser(Long id, User user, Channel channel) {
        this.id = id;
        this.user = user;
        this.channel = channel;
        this.createTime = now();
        this.updateTime = this.createTime;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Channel getChannel() {
        return channel;
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public OffsetDateTime getUpdateTime() {
        return updateTime;
    }
}
