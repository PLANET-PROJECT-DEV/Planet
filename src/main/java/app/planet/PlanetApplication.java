package app.planet;

import app.planet.config.netty.CoordinationNettyServer;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlanetApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(PlanetApplication.class, args);
    }

    @Resource
    private CoordinationNettyServer coordinationNettyServer;

    @Override
    public void run(String... args) throws Exception {
        coordinationNettyServer.start();
    }
}
