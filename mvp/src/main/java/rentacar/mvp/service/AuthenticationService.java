package rentacar.mvp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rentacar.mvp.configuration.security.JWTUtil;
import rentacar.mvp.controller.authenticate.request.ChangePasswordRequest;
import rentacar.mvp.controller.authenticate.request.CreateAdminRequest;
import rentacar.mvp.controller.authenticate.request.SignInRequest;
import rentacar.mvp.controller.authenticate.response.SignInResponse;
import rentacar.mvp.enumeration.Role;
import rentacar.mvp.model.User;
import rentacar.mvp.repository.jpa.UserRepository;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by savagaborov on 12.1.2020
 */
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

    @Transactional(rollbackFor = Exception.class)
    public void createInitAdminUser(CreateAdminRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.SUPER_ADMIN);
        user.setPhoneNumber(request.getPhoneNumber());
        userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void registerAdminUser(CreateAdminRequest request) throws Exception {
        log.info("START registerAdminUser()");

        Optional<User> existingUser = userRepository.getUserByEmailAndDeletedIsFalse(request.getEmail());
        if (existingUser.isPresent()) {
            throw new Exception("The email address already registered");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ADMIN);
        user.setPhoneNumber(request.getPhoneNumber());
        userRepository.save(user);

        log.info("FINISH registerAdminUser()");
    }

    public SignInResponse signIn(SignInRequest request) throws Exception {
        log.info("START signIn()");

        Optional<User> user = userRepository.getUserByEmailAndDeletedIsFalse(request.getEmail());
        if (!user.isPresent()) {
            throw new Exception("The user does not exist");
        }

        if(!passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            throw new Exception("Old password is not correct");
        }

        log.info("FINISH signIn()");
        return SignInResponse.builder()
                .firstName(user.get().getFirstName())
                .lastName(user.get().getLastName())
                .email(user.get().getEmail())
                .phoneNumber(user.get().getPhoneNumber())
                .role(user.get().getRole())
                .accessToken(JWTUtil.generateAccessToken(user.get()))
                .refreshToken(JWTUtil.generateRefreshToken(user.get()))
                .build();
    }

    public void changePassword(ChangePasswordRequest request) throws Exception {
        log.info("START changePassword()");

        Optional<User> user = userRepository.getUserByEmailAndDeletedIsFalse(request.getEmail());
        if (user.isPresent()) {
            throw new Exception("The user does not exist");
        }

        if(passwordEncoder.matches(request.getOldPassword(), user.get().getPassword())) {
            throw new Exception("Old password is not correct");
        }

        user.get().setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user.get());

        log.info("FINISH changePassword()");
    }
}
