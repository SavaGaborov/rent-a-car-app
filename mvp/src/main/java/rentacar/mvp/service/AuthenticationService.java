package rentacar.mvp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rentacar.mvp.controller.authenticate.request.CreateAdminRequest;
import rentacar.mvp.model.User;
import rentacar.mvp.repository.jpa.UserRepository;

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
        log.info("START createInitAdminUser()");

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        userRepository.save(user);

        log.info("FINISH createInitAdminUser()");
    }
}
