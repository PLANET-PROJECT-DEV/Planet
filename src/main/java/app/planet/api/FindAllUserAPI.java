package app.planet.api;


import app.planet.application.FindAllUserApplicationService;
import app.planet.domain.model.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FindAllUserAPI {

    private FindAllUserApplicationService findAllUserApplicationService;

    public FindAllUserAPI(FindAllUserApplicationService findAllUserApplicationService) {
        this.findAllUserApplicationService = findAllUserApplicationService;
    }

    @GetMapping("/findAllUser")
    public List<User> findAllUser(){
        return findAllUserApplicationService.findAllUser();
    }
}
