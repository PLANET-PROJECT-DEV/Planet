package app.planet.core.rest;


import app.planet.application.command.user.UserBasicCommand;
import app.planet.core.context.UserHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

import static app.planet.core.constant.RedisConstant.LOGIN_USER_KEY;
import static app.planet.core.constant.RedisConstant.LOGIN_USER_TTL;
import static java.util.concurrent.TimeUnit.MINUTES;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.cors.CorsUtils.isPreFlightRequest;

@Component
public class RefreshTokenInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate=stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (isPreFlightRequest(request)) {
            response.setStatus(OK.value());
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setHeader("Access-Control-Allow-Headers", "*");
            return false;
        }
        String token = request.getHeader("authorization");
        System.out.println(token);
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
            Exception ex) {
        UserHolder.removeUser();
    }
}
