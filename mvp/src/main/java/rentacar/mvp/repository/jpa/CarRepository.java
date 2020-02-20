package rentacar.mvp.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import rentacar.mvp.enumeration.CarBrand;
import rentacar.mvp.enumeration.CarType;
import rentacar.mvp.model.Car;

import java.util.List;
import java.util.Optional;

/**
 * Created by savagaborov on 8.2.2020
 */
public interface CarRepository extends JpaRepository<Car, Long>{

    Optional<Car> getCarByRegistrationNumberAndDeletedFalse (String registrationNumber);

    Optional<Car> getCarByIdAndDeletedIsFalse (Long id);

    List<Car> getCarsByCarBrandAndCarTypeAndAvailableIsTrueAndDeletedFalse(CarBrand carBrand, CarType carType);
}
