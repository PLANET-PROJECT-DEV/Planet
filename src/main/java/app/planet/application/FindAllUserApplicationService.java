package app.planet.application;

import app.planet.domain.mapper.UserMapper;
import app.planet.domain.model.user.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FindAllUserApplicationService{

    @Resource
    private  UserMapper userMapper;

    public List<User> findAllUser(){
        return userMapper.findAllUser();
    }
}
