package app.planet.api;


import app.planet.application.UserLoginApplicationService;
import app.planet.application.command.CreateUserCommand;
import app.planet.application.command.LoginPasswordCommand;
import app.planet.application.command.SendCodeCommand;
import app.planet.application.result.UserLoginResult;
import app.planet.domain.exception.InvalidCodeException;
import app.planet.domain.exception.InvalidUserInfoException;
import app.planet.domain.exception.PasswordErrException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserCommandRestAPI {

    private final UserLoginApplicationService userLoginApplicationService;


    public UserCommandRestAPI(UserLoginApplicationService userLoginApplicationService) {
        this.userLoginApplicationService = userLoginApplicationService;
    }

    @PostMapping("/login")
    public UserLoginResult userLogin(@RequestBody CreateUserCommand command) throws  InvalidUserInfoException, InvalidCodeException {
        return userLoginApplicationService.userLoginWithCode(command);
    }

    @PostMapping("/loginWithPassword")
    public UserLoginResult userLoginWithPassword(@RequestBody LoginPasswordCommand command) throws PasswordErrException {
        return userLoginApplicationService.userLoginWithPassword(command);
    }

    @PostMapping("/code")
    public void sendCode(@RequestBody SendCodeCommand command) throws InvalidUserInfoException {
        userLoginApplicationService.sendCodeByEmail(command);
    }

    @PostMapping("/logout")
    public void userLogout(@RequestParam("token") String token) {
        userLoginApplicationService.logout(token);
    }

}
