package app.planet.domain.Repository;

import app.planet.domain.model.channel_mtm_user.ChannelMtmUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChannelMtmUserRepository extends JpaRepository<ChannelMtmUser,Long> {
    Optional<ChannelMtmUser> findAllByChannelId(Long channelId);
}
