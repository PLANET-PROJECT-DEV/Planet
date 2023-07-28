package app.planet.domain.Repository;

import app.planet.domain.model.channel.Channel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChannelRepository extends JpaRepository<Channel,Long> {
    Channel findChannelById(Long Id);
}
