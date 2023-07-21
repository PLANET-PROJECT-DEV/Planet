package app.planet.core.rest;


import app.planet.application.command.UserBasicCommand;
import app.planet.core.context.UserHolder;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import java.util.Map;

import static app.planet.core.constant.RedisConstant.LOGIN_USER_KEY;
import static app.planet.core.constant.RedisConstant.LOGIN_USER_TTL;
import static java.util.concurrent.TimeUnit.MINUTES;

@Component
public class RefreshTokenInterceptor implements HandlerInterceptor {


    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Autowired
    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate=stringRedisTemplate;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("authorization");
        if (token.isBlank()) {
            return true;
        }
        Map<Object, Object> userMap = stringRedisTemplate
                .opsForHash().entries(LOGIN_USER_KEY + token.split(" ")[1]);
        if (userMap.isEmpty()) {
            return true;
        }
        UserBasicCommand userBasicCommand = new UserBasicCommand(
                Long.valueOf(userMap.get("id").toString()),
                userMap.get("nickName").toString(),
                userMap.get("icon").toString()
        );
        UserHolder.saveUser(userBasicCommand);
        //刷新token有效期
        stringRedisTemplate
                .expire(LOGIN_USER_KEY + token.split(" ")[1], LOGIN_USER_TTL, MINUTES);
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) throws Exception {
        UserHolder.removeUser();
    }
}
