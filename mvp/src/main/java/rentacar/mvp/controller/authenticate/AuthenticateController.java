package rentacar.mvp.controller.authenticate;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rentacar.mvp.controller.authenticate.request.*;
import rentacar.mvp.controller.authenticate.response.RefreshTokenResponse;
import rentacar.mvp.controller.authenticate.response.SignInResponse;
import rentacar.mvp.controller.staff.request.CreateStaffRequest;
import rentacar.mvp.service.AuthenticationService;

import javax.validation.Valid;

/**
 * Created by savagaborov on 8.1.2020
 */
@RestController
@RequestMapping("/authentication")
public class AuthenticateController {

    private final Logger log = LoggerFactory.getLogger(AuthenticateController.class);

    private final AuthenticationService authenticationService;

    public AuthenticateController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @ApiOperation("Initial create super admin user")
    @PostMapping (value="/add/super-admin/temporary")
    @ResponseStatus(HttpStatus.CREATED)
    public void createInitSuperAdminUser(@RequestBody @Valid CreateStaffRequest request) {
        authenticationService.createInitSuperAdminUser(request);
    }

    @ApiOperation("Sign in")
    @PostMapping (value="/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public SignInResponse signIn(@RequestBody @Valid SignInRequest request) throws Exception {
        return authenticationService.signIn(request);
    }

    @ApiOperation("Change user's password")
    @PostMapping (value="/change-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('STAFF') or hasAuthority('BORROWER')")
    public void changePassword(@RequestBody @Valid ChangePasswordRequest request) throws Exception {
        authenticationService.changePassword(request);
    }

    @ApiOperation("Forgot password")
    @PostMapping (value="/forgot-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void forgotPassword(@RequestBody @Valid ForgotPasswordRequest request) throws Exception {
        authenticationService.forgotPassword(request);
    }

    @ApiOperation("Reset password")
    @PostMapping (value="/reset-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void resetPassword(@RequestBody @Valid ResetPasswordRequest request, @RequestParam String resetPasswordCode) throws Exception {
        authenticationService.resetPassword(request,resetPasswordCode);
    }

    @ApiOperation("Refresh access token")
    @PostMapping(value = "/token/refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String refreshToken) throws Exception {
        log.debug("POST /authentication/token/refresh {}", refreshToken);
        RefreshTokenResponse response = authenticationService.refreshToken(refreshToken);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
