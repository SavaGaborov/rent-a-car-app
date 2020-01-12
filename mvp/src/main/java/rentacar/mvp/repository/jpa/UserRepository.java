package rentacar.mvp.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rentacar.mvp.model.User;

/**
 * Created by savagaborov on 12.1.20..
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
