package rentacar.mvp.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rentacar.mvp.configuration.security.JWTFilter;
import rentacar.mvp.configuration.security.JWTUtil;
import rentacar.mvp.controller.authenticate.request.*;
import rentacar.mvp.controller.authenticate.response.RefreshTokenResponse;
import rentacar.mvp.controller.authenticate.response.SignInResponse;
import rentacar.mvp.controller.exception.RentacarException;
import rentacar.mvp.controller.staff.request.CreateStaffRequest;
import rentacar.mvp.enumeration.Role;
import rentacar.mvp.model.User;
import rentacar.mvp.repository.jpa.UserRepository;
import org.springframework.mail.javamail.JavaMailSender;

import javax.validation.Valid;
import java.time.ZonedDateTime;
import java.util.Optional;

import static rentacar.mvp.configuration.security.JWTUtil.*;
import static rentacar.mvp.util.EmailUtil.sendEmail;
/**
 * Created by savagaborov on 12.1.2020
 */
@Service
public class AuthenticationService {

    private final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Environment env;
    private final JavaMailSender javaMailSender;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JavaMailSender javaMailSender, Environment env){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
        this.env = env;
    }

    @Transactional(rollbackFor = Exception.class)
    public void createInitSuperAdminUser(CreateStaffRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.SUPER_ADMIN);
        user.setPhoneNumber(request.getPhoneNumber());
        userRepository.save(user);
    }

    public SignInResponse signIn(SignInRequest request) throws Exception {
        log.info("START signIn()");

        Optional<User> user = userRepository.getUserByEmailAndDeletedIsFalse(request.getEmail());
        if (!user.isPresent()) {
            throw new RentacarException("user.not.exist");
        }

        if(!passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            throw new RentacarException("old.password.not.valid");
        }

        log.info("FINISH signIn()");
        return SignInResponse.builder()
                .firstName(user.get().getFirstName())
                .lastName(user.get().getLastName())
                .email(user.get().getEmail())
                .phoneNumber(user.get().getPhoneNumber())
                .role(user.get().getRole())
                .accessToken(generateAccessToken(user.get()))
                .refreshToken(generateRefreshToken(user.get()))
                .build();
    }

    public void changePassword(ChangePasswordRequest request) throws Exception {
        log.info("START changePassword()");

        Optional<User> user = userRepository.getUserByEmailAndDeletedIsFalse(request.getEmail());
        if (user.isPresent()) {
            throw new RentacarException("user.not.exist");
        }

        if(passwordEncoder.matches(request.getOldPassword(), user.get().getPassword())) {
            throw new RentacarException("old.password.not.valid");
        }

        user.get().setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user.get());

        log.info("FINISH changePassword()");
    }

    public void forgotPassword(@Valid ForgotPasswordRequest request) throws Exception {
        log.info("START forgotPassword()");

        Optional<User> user = userRepository.getUserByEmailAndDeletedIsFalse(request.getEmail());
        if (!user.isPresent()) {
            throw new RentacarException("user.not.exist");
        }

        user.get().setResetPasswordCode(RandomString.make(8));
        user.get().setResetPasswordCodeTimestamp(ZonedDateTime.now());
        userRepository.save(user.get());

        String subject = "Forgot password";
        String body = "Reset your password by clicking on the link: http://localhost:8080/reset-password/" + user.get().getResetPasswordCode();
        sendEmail(request.getEmail(), subject, body);

        log.info("FINISH forgotPassword()");
    }

    public void resetPassword(ResetPasswordRequest request, String resetPasswordCode) {
        log.info("START resetPassword()");

        Optional<User> user = userRepository.getUserByEmailAndDeletedIsFalse(request.getEmail());
        if (!user.isPresent()) {
            throw new RentacarException("user.not.exist");
        }

        if (!user.get().getResetPasswordCode().equals(resetPasswordCode) || user.get().getResetPasswordCodeTimestamp().plusHours(1).isAfter(ZonedDateTime.now())) {
            throw new RentacarException("reset.password.code.not.valid");
        }

        user.get().setResetPasswordCode(null);
        user.get().setResetPasswordCodeTimestamp(null);
        user.get().setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user.get());

        log.info("FINISH resetPassword()");
    }

    public RefreshTokenResponse refreshToken(String refreshToken) throws Exception {

        String extractedRefreshToken = JWTFilter.extractRefreshToken(refreshToken);
        final User user = getUserByJwt(extractedRefreshToken);
        final String newAccessToken = generateAccessToken(user);
        final String newRefreshToken = generateRefreshToken(user);

        return new RefreshTokenResponse(user.getId(), newAccessToken, newRefreshToken, ACCESS_TOKEN_VALIDITY_IN_MINUTES);
    }

    private User getUserByJwt(String jwtToken) throws RentacarException {
        try {
            final Claims claims = Jwts.parser().setSigningKey(REFRESH_SECRET_KEY).parseClaimsJws(jwtToken).getBody();
            Optional<User> user = userRepository.findById(Long.valueOf(claims.getSubject()));
            if(user.isPresent()){
                return user.get();
            } else {
                throw new RentacarException("general.exception", HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            throw new RentacarException("invalid.token", HttpStatus.CONFLICT);
        }
    }
}
