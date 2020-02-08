package rentacar.mvp.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import rentacar.mvp.model.Car;

/**
 * Created by savagaborov on 8.2.2020
 */
public interface CarRepository extends JpaRepository<Car, Long>{
}
