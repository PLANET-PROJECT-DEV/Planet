package app.planet.api.console;


import app.planet.application.PlanetDataApplicationService;
import app.planet.application.command.planet.GetPlantUsersLatelyCommand;
import app.planet.application.result.planet.GetPlanetNewUsersResult;
import app.planet.application.result.planet.GetPlanetUsersLatelyResult;
import app.planet.application.result.planet.GetPlanetUsersResult;
import app.planet.domain.exception.PlanetNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/console")
public class ConsoleDataRestAPI {
    private final PlanetDataApplicationService planetDataApplicationService;

    public ConsoleDataRestAPI(PlanetDataApplicationService planetDataApplicationService) {
        this.planetDataApplicationService = planetDataApplicationService;
    }

    @PostMapping("/users")
    public GetPlanetUsersResult getPlanetUsers() throws PlanetNotFoundException {
        return planetDataApplicationService.getPlanetUsers();
    }

    @PostMapping("/usersAgo")
    public GetPlanetUsersLatelyResult getPlanetUsersLately(@RequestBody GetPlantUsersLatelyCommand getPlantUsersLatelyCommand) throws PlanetNotFoundException {
        return planetDataApplicationService.getPlanetUsersLatelyResult(getPlantUsersLatelyCommand.daysAgo());
    }

    @PostMapping("/newUsers")
    public GetPlanetNewUsersResult getPlanetNewUsers() throws PlanetNotFoundException {
        return planetDataApplicationService.getPlanetNewUsers();
    }

    @PostMapping("/headers")
    public void listHeaders(
            @RequestHeader Map<String,String> headers
    ) {headers.forEach((key,value)->{
        System.out.println(key+":"+value);
    });
    }
}
