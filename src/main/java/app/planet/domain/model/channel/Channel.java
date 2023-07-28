package app.planet.domain.model.channel;


import app.planet.domain.exception.InvalidChannelInfoInfoException;

import app.planet.utils.Randoms;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.OffsetDateTime.now;

@Entity
public class Channel {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long Id;
    private String channelName;
    private String channelNumber;
    private String channelType;
    private Integer memberCount;
    private OffsetDateTime createTime;
    private OffsetDateTime updateTime;

    public Channel() {
    }

    public Channel(ChannelInfo channelInfo) throws InvalidChannelInfoInfoException {
        if (!channelInfo.isValid()) {
            throw new InvalidChannelInfoInfoException();
        }
        this.channelNumber = Randoms.aRandomText(8);
        this.channelName= channelInfo.channelName();
        this.channelType= channelInfo.channelType();
        this.memberCount= 0;
        this.createTime = now();
        this.updateTime = this.createTime;
    }

    public Long getId() {return Id;}

    public String getChannelName() {
        return channelName;
    }

    public String getChannelNumber() {
        return channelNumber;
    }

    public String getChannelType() {
        return channelType;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public OffsetDateTime getUpdateTime() {
        return updateTime;
    }
}
