package app.planet.domain.mapper;

import app.planet.domain.model.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<User> findAllUser();
}
