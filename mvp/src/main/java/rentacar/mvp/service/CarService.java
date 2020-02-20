package rentacar.mvp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rentacar.mvp.controller.car.request.AddNewCarRequest;
import rentacar.mvp.controller.car.response.GetCarResponse;
import rentacar.mvp.controller.exception.RentacarException;
import rentacar.mvp.enumeration.CarBrand;
import rentacar.mvp.enumeration.CarType;
import rentacar.mvp.model.Car;
import rentacar.mvp.repository.jpa.CarRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by savagaborov on 8.2.2020
 */
@Service
public class CarService {

    private final Logger log = LoggerFactory.getLogger(CarService.class);

    private final CarRepository carRepository;

    public CarService (CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public void addNewCar(@Valid AddNewCarRequest request) {
        log.info("START addNewCar(request: {})", request);

        Optional<Car> existingCar = carRepository.getCarByRegistrationNumberAndDeletedFalse(request.getRegistration_number());
        if (existingCar.isPresent()) {
            throw new RentacarException("car.already.exist");
        }

        Car car = new Car();
        car.setCarBrand(request.getCarBrand());
        car.setModel(request.getModel());
        car.setCarType(request.getCarType());
        car.setRegistrationNumber(request.getRegistration_number());
        car.setYearBuilt(request.getYear_built());
        car.setAvailable(request.getAvailable());
        carRepository.save(car);

        log.info("FINISH addNewCar()");
    }

    public GetCarResponse getCar(Long carId) {
        log.info("START getCar(carId: {})", carId);

        Optional<Car> existingCar = carRepository.getCarByIdAndDeletedIsFalse(carId);
        if (!existingCar.isPresent()) {
            throw new RentacarException("car.not.exist");
        }

        log.info("FINISH getCar()");
        return GetCarResponse.builder()
                .id(existingCar.get().getId())
                .carBrand(existingCar.get().getCarBrand())
                .model(existingCar.get().getModel())
                .type(existingCar.get().getCarType())
                .registrationNumber(existingCar.get().getRegistrationNumber())
                .yearBuilt(existingCar.get().getYearBuilt())
                .timesBorrowed(existingCar.get().getTimesBorrowed())
                .build();
    }

    public void deleteCar(Long carId) {
        log.info("START deleteCar(carId: {})", carId);

        Optional<Car> existingCar = carRepository.getCarByIdAndDeletedIsFalse(carId);
        if (!existingCar.isPresent()) {
            throw new RentacarException("car.not.exist");
        }

        Car car = existingCar.get();
        car.setDeleted(true);
        carRepository.save(car);

        log.info("FINISH deleteCar()");
    }

    public void outOfOrderCar(Long carId) {
        log.info("START outOfOrderCar(carId: {})", carId);

        Optional<Car> existingCar = carRepository.getCarByIdAndDeletedIsFalse(carId);
        if (!existingCar.isPresent()) {
            throw new RentacarException("car.not.exist");
        }

        Car car = existingCar.get();
        car.setAvailable(false);
        carRepository.save(car);

        log.info("FINISH outOfOrderCar()");
    }

    public List<Car> getAvailableCars(CarBrand carBrand, CarType carType) {
        log.info("START getAvailableCars(carBrand: {}, carType: {})", carBrand, carType);

        List<Car> availableCars = carRepository.getCarsByCarBrandAndCarTypeAndAvailableIsTrueAndDeletedFalse(carBrand, carType);

        log.info("FINISH getAvailableCars()");
        return availableCars;
    }
}
