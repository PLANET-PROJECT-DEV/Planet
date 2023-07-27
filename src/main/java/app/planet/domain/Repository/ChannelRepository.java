package app.planet.domain.Repository;

import app.planet.domain.model.channel.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChannelRepository extends JpaRepository<Channel,Long> {


    Channel findByChannelId(Long channelId);

    void delete(Channel channel);
}
