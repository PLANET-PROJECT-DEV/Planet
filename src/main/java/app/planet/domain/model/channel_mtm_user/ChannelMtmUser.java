package app.planet.domain.model.channel_mtm_user;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.OffsetDateTime.now;

@Entity
public class ChannelMtmUser {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Long userId;
    private Long channelId;
    private Role role;
    private OffsetDateTime createTime;
    private OffsetDateTime updateTime;

    public ChannelMtmUser() {
    }

    public ChannelMtmUser(Long id, Long userId, Long channelId) {
        this.id = id;
        this.userId = userId;
        this.channelId = channelId;
        this.role = Role.NORMAL;
        this.createTime = now();
        this.updateTime = this.createTime;
    }

    public enum Role {
        NORMAL,BAN,ADMIN
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public Role getRole() {
        return role;
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public OffsetDateTime getUpdateTime() {
        return updateTime;
    }

}
