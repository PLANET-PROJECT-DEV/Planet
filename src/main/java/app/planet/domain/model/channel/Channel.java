package app.planet.domain.model.channel;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Channel {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String channelName;
    private String channelNumber;
    private String channelType;

    public Channel() {
    }

    public Channel(String channelName, String channelNumber, String channelType) {
        this.channelName = channelName;
        this.channelNumber = channelNumber;
        this.channelType = channelType;
    }


    public String getChannelName() {
        return channelName;
    }

    public String getChannelNumber() {
        return channelNumber;
    }

    public String getChannelType() {
        return channelType;
    }

    public Long getId() {
        return id;
    }

}
