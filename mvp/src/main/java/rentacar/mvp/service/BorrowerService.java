package rentacar.mvp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rentacar.mvp.controller.borrower.request.CreateBorrowerRequest;
import rentacar.mvp.controller.borrower.request.EditBorrowerRequest;
import rentacar.mvp.controller.borrower.response.GetBorrowerResponse;
import rentacar.mvp.controller.borrower.response.GetBorrowerUsersResponse;
import rentacar.mvp.controller.exception.RentacarException;
import rentacar.mvp.enumeration.Role;
import rentacar.mvp.model.User;
import rentacar.mvp.repository.jpa.UserRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static rentacar.mvp.controller.Converter.toGetBorrowerResponse;

/**
 * Created by savagaborov on 26.1.2020
 */
@Service
public class BorrowerService {

    private final Logger log = LoggerFactory.getLogger(BorrowerService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public BorrowerService(UserRepository userRepository, PasswordEncoder passwordEncoder, JavaMailSender javaMailSender, Environment env){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(rollbackFor = Exception.class)
    public void createBorrowerUser(@Valid CreateBorrowerRequest request) {
        log.info("START createBorrowerUser(request: {})", request);

        Optional<User> existingUser = userRepository.getUserByEmailAndDeletedIsFalse(request.getEmail());
        if (existingUser.isPresent()) {
            throw new RentacarException("user.already.registered");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.BORROWER);
        user.setPhoneNumber(request.getPhoneNumber());
        userRepository.save(user);

        log.info("FINISH createBorrowerUser()");
    }

    public GetBorrowerUsersResponse getBorrowerUsers() {
        log.info("START getBorrowerUsers()");

        List<User> staffUsers = userRepository.getUsersByRole(Role.BORROWER.toString());

        log.info("START getBorrowerUsers()");
        return new GetBorrowerUsersResponse(staffUsers.stream().map(user -> toGetBorrowerResponse(user)).collect(Collectors.toList()));
    }

    public GetBorrowerResponse getBorrowerUser(Long borrowerId) {
        log.info("START getBorrowerUser(borrowerId: {})", borrowerId);

        Optional<User> existingUser = userRepository.findById(borrowerId);
        if (!existingUser.isPresent()) {
            throw new RentacarException("user.not.exist");
        }

        log.info("FINISH getBorrowerUser()");
        return toGetBorrowerResponse(existingUser.get());
    }

    @Transactional(rollbackFor = Exception.class)
    public void editBorrowerUser(Long borrowerId, @Valid EditBorrowerRequest request) {
        log.info("START editBorrowerUser(request: {}, borrowerId: {})", request, borrowerId);

        Optional<User> existingUser = userRepository.findById(borrowerId);
        if (!existingUser.isPresent()) {
            throw new RentacarException("user.not.exist");
        }

        User user = existingUser.get();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        userRepository.save(user);

        log.info("FINISH editBorrowerUser()");
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteBorrowerUser(Long borrowerId) {
        log.info("START deleteBorrowerUser(borrowerId: {})", borrowerId);

        Optional<User> existingUser = userRepository.findById(borrowerId);
        if (!existingUser.isPresent()) {
            throw new RentacarException("user.not.exist");
        }

        User user = existingUser.get();
        user.setDeleted(true);
        userRepository.save(user);

        log.info("FINISH deleteBorrowerUser()");
    }
}
