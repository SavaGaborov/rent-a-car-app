package rentacar.mvp.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rentacar.mvp.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by savagaborov on 12.1.20..
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> getUserByEmailAndDeletedIsFalse(String email);

    List<User> getUsersByRole(String Role);
}
