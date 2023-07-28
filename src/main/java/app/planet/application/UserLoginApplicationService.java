package app.planet.application;

import app.planet.application.command.CreateUserCommand;
import app.planet.application.command.LoginPasswordCommand;
import app.planet.application.command.SendCodeCommand;
import app.planet.application.command.UserBasicCommand;
import app.planet.application.result.UserLoginResult;
import app.planet.domain.Repository.UserRepository;
import app.planet.domain.exception.InvalidCodeException;
import app.planet.domain.exception.InvalidUserInfoException;
import app.planet.domain.exception.PasswordErrException;
import app.planet.domain.model.user.User;
import app.planet.domain.model.user.UserInfo;
import app.planet.utils.Randoms;

import jakarta.annotation.Resource;


import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static app.planet.core.constant.RedisConstant.*;
import static app.planet.core.mail.MailSender.*;
import static java.util.concurrent.TimeUnit.MINUTES;

@Service
@Transactional
public class UserLoginApplicationService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    // keypoint: 邮箱验证码注册&登录
    public UserLoginResult userLoginWithCode(CreateUserCommand userCommand) throws InvalidUserInfoException, InvalidCodeException {
        User newUser = new User(new UserInfo(userCommand.email()));
        String cacheCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + userCommand.email());
        String code = userCommand.code();
        if (cacheCode == null || !cacheCode.equals(code)) {
            throw new InvalidCodeException();
        }
        if (!userRepository.existsByEmail(newUser.getEmail())) {
            userRepository.save(newUser);
        }
        User user = userRepository.findByEmail(newUser.getEmail());
        return getUserLoginResult(user);
    }


    // keypoint: 邮箱发送登录验证码
    public void sendCodeByEmail(SendCodeCommand command) throws InvalidUserInfoException {
        System.out.println(command.email());
        User user = new User(new UserInfo(command.email()));
        String code = Randoms.aRandomSuffix();
        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + command.email(), code, LOGIN_CODE_TTL, MINUTES);
        sendMail(command.email(), LOGIN_CODE_MESSAGE + code + LOGIN_CODE_INFORMATION, LOGIN_CODE_TITLE);
    }

    //keypoint: 账号密码登录
    public UserLoginResult userLoginWithPassword(LoginPasswordCommand loginPasswordCommand) throws PasswordErrException {
        String password = loginPasswordCommand.password();
        User user = userRepository.findByEmail(loginPasswordCommand.email());
        String truePassword = userRepository.findByEmail(user.getEmail()).getPassword();
        if (!password.equals(truePassword)) {
            throw new PasswordErrException();
        }
        return getUserLoginResult(user);
    }

    //keypoint: 登出
    public void logout(String token) {
        stringRedisTemplate.opsForHash().delete(LOGIN_USER_KEY + token, "id");
    }


    //keypoint: 储存登录信息
    private UserLoginResult getUserLoginResult(User user) {
        String token = UUID.randomUUID().toString();
        UserBasicCommand userBasicCommand = new UserBasicCommand(user.getUserId(), user.getNickName(), user.getIcon());
        // hashMap储存user信息
        Map<String, Object> userMap =  new HashMap<>();
        userMap.put("id",userBasicCommand.getId().toString());
        userMap.put("icon",userBasicCommand.getIcon());
        userMap.put("nickName",userBasicCommand.getNickName());

        stringRedisTemplate.opsForHash().putAll(LOGIN_USER_KEY + token, userMap);
        stringRedisTemplate.expire(LOGIN_USER_KEY + token, LOGIN_USER_TTL, MINUTES);
        return new UserLoginResult(token);
    }
}
