package rentacar.mvp.controller.authenticate;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rentacar.mvp.controller.authenticate.request.CreateAdminRequest;
import rentacar.mvp.service.AuthenticationService;

import javax.validation.Valid;

/**
 * Created by savagaborov on 8.1.2020
 */
@RestController
@RequestMapping("/authentication")
public class AuthenticateController {

    private final AuthenticationService authenticationService;

    public AuthenticateController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping(value="/test", produces = MediaType.TEXT_PLAIN_VALUE)
    public String index() {
        return "This is TEST API";
    }

    @PostMapping (value="/create/admin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createInitAdminUser(@RequestBody @Valid CreateAdminRequest request) {
        authenticationService.createInitAdminUser(request);
    }
}
