package app.planet.config.interceptor;

import app.planet.core.rest.LoginInterceptor;
import app.planet.core.rest.RefreshTokenInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns(
                        "/user/code",
                        "/user/login",
                        "/user/loginWithPassword",
                        "/console/headers",
                        "/admin/common/upload",
                        "/ws",
                        "/chat/messageRecord",
                        "/chat/**"
                ).order(1);
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate))
                .excludePathPatterns(
                        "/user/code",
                        "/user/login",
                        "/user/loginWithPassword",
                        "/console/headers",
                        "/ws",
                        "/chat/messageRecord",
                        "/chat/**"
                )
                .addPathPatterns(
                        "/**"
                ).order(0);
    }
}
