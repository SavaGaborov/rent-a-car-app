package rentacar.mvp.controller.authenticate;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rentacar.mvp.controller.authenticate.request.ChangePasswordRequest;
import rentacar.mvp.controller.authenticate.request.CreateAdminRequest;
import rentacar.mvp.controller.authenticate.request.ForgotPasswordRequest;
import rentacar.mvp.controller.authenticate.request.SignInRequest;
import rentacar.mvp.controller.authenticate.response.SignInResponse;
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

    @PostMapping (value="/add/super-admin/temporary")
    @ResponseStatus(HttpStatus.CREATED)
    public void createInitAdminUser(@RequestBody @Valid CreateAdminRequest request) {
        authenticationService.createInitAdminUser(request);
    }

    @PostMapping (value="/admin/register")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public void registerAdminUser(@RequestBody @Valid CreateAdminRequest request) throws Exception {
        authenticationService.registerAdminUser(request);
    }

    @PostMapping (value="/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public SignInResponse signIn(@RequestBody @Valid SignInRequest request) throws Exception {
        return authenticationService.signIn(request);
    }

    @PostMapping (value="/change-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN') or hasAuthority('BORROWER')")
    public void changePassword(@RequestBody @Valid ChangePasswordRequest request) throws Exception {
        authenticationService.changePassword(request);
    }

    @PostMapping (value="/forgot-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void forgotPassword(@RequestBody @Valid ForgotPasswordRequest request) throws Exception {
        authenticationService.forgotPassword(request);
    }

    @PostMapping (value="/reset-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN') or hasAuthority('BORROWER')")
    public void resetPassword(@RequestParam String resetPasswordCode) throws Exception {
        authenticationService.resetPassword(resetPasswordCode);
    }
}
