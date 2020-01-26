package rentacar.mvp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rentacar.mvp.controller.exception.RentacarException;
import rentacar.mvp.controller.staff.request.CreateStaffRequest;
import rentacar.mvp.controller.staff.request.EditStaffRequest;
import rentacar.mvp.controller.staff.response.GetStaffResponse;
import rentacar.mvp.controller.staff.response.GetStaffUsersResponse;
import rentacar.mvp.enumeration.Role;
import rentacar.mvp.model.User;
import rentacar.mvp.repository.jpa.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static rentacar.mvp.controller.Converter.toGetStaffResponse;

/**
 * Created by savagaborov on 20.1.2020
 */
@Service
public class StaffService {

    private final Logger log = LoggerFactory.getLogger(StaffService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public StaffService(UserRepository userRepository, PasswordEncoder passwordEncoder, JavaMailSender javaMailSender, Environment env){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(rollbackFor = Exception.class)
    public void createStaffUser(CreateStaffRequest request) throws Exception {
        log.info("START createStaffUser(request: {})", request);

        Optional<User> existingUser = userRepository.getUserByEmailAndDeletedIsFalse(request.getEmail());
        if (existingUser.isPresent()) {
            throw new RentacarException("user.already.registered");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.STAFF);
        user.setPhoneNumber(request.getPhoneNumber());
        userRepository.save(user);

        log.info("FINISH createStaffUser()");
    }

    public GetStaffUsersResponse getStaffUsers() {
        log.info("START getStaffUsers()");

        List<User> staffUsers = userRepository.getUsersByRole(Role.STAFF.toString());

        log.info("START getStaffUsers()");
        return new GetStaffUsersResponse(staffUsers.stream().map(user -> toGetStaffResponse(user)).collect(Collectors.toList()));
    }

    public GetStaffResponse getStaffUser(Long staffId) {
        log.info("START getStaffUser(staffId: {})", staffId);

        Optional<User> existingUser = userRepository.findById(staffId);
        if (!existingUser.isPresent()) {
            throw new RentacarException("user.not.exist");
        }

        log.info("FINISH getStaffUser()");
        return toGetStaffResponse(existingUser.get());
    }

    @Transactional(rollbackFor = Exception.class)
    public void editStaffUser(Long staffId, EditStaffRequest request) {
        log.info("START editStaffUser(request: {}, staffId: {})", request, staffId);

        Optional<User> existingUser = userRepository.findById(staffId);
        if (!existingUser.isPresent()) {
            throw new RentacarException("user.not.exist");
        }

        User user = existingUser.get();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        userRepository.save(user);

        log.info("FINISH editStaffUser()");
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteStaffUser(Long staffId) {
        log.info("START deleteStaffUser(staffId: {})", staffId);

        Optional<User> existingUser = userRepository.findById(staffId);
        if (!existingUser.isPresent()) {
            throw new RentacarException("user.not.exist");
        }

        User user = existingUser.get();
        user.setDeleted(true);
        userRepository.save(user);

        log.info("FINISH deleteStaffUser()");
    }
}
