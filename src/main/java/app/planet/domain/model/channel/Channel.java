package app.planet.domain.model.channel;


import app.planet.domain.exception.InvalidChannelInfoInfoException;


import app.planet.domain.model.channel_mtm_user.ChannelMtmUser;
import app.planet.domain.model.user.User;
import app.planet.utils.Randoms;
import jakarta.persistence.*;
import org.hibernate.annotations.Table;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.OffsetDateTime.now;

@Entity
@Table(appliesTo = "channel")
public class Channel {


    @Id
    @Column(name = "channel_id", nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long channelId;
    private String channelName;
    private String channelNumber;
    private String channelType;
    private Integer memberCount;
    private OffsetDateTime createTime;
    private OffsetDateTime updateTime;
    @OneToMany(mappedBy = "channel")
    private Set<ChannelMtmUser> channelMtmUsers;

    public Channel() {
    }

    public Channel(ChannelInfo channelInfo) throws InvalidChannelInfoInfoException {
        if (!channelInfo.isValid()) {
            throw new InvalidChannelInfoInfoException();
        }
        this.channelNumber = Randoms.aRandomText(8);
        this.channelName=channelInfo.channelName();
        this.channelType= channelInfo.channelType();
        this.createTime = now();
        this.updateTime = this.createTime;
    }

    public Long getChannelId() {return channelId;}
    public String getChannelName() {
        return channelName;
    }

    public String getChannelNumber() {
        return channelNumber;
    }

    public String getChannelType() {
        return channelType;
    }
}
