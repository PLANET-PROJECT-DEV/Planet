package app.planet.api;


import app.planet.application.PlanetDataApplicationService;
import app.planet.application.result.GetPlanetUsersResult;
import app.planet.domain.exception.PlanetNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/console")
public class ConsoleDataRestAPI {
    private final PlanetDataApplicationService planetDataApplicationService;

    public ConsoleDataRestAPI(PlanetDataApplicationService planetDataApplicationService) {
        this.planetDataApplicationService = planetDataApplicationService;
    }

    @PostMapping("/planet")
    public GetPlanetUsersResult getPlanetUsersResult() throws PlanetNotFoundException {
        return planetDataApplicationService.getPlanetUsersResult();
    }
}
