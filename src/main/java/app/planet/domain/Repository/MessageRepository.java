package app.planet.domain.Repository;

import app.planet.domain.model.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findAllByFromAndTo(Long from,Long to);

    List<Message> findAllByTo(Long to);
}
