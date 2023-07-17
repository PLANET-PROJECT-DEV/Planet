package app.planet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("app.planet.domain.mapper")
public class PlanetApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlanetApplication.class, args);
    }
}
