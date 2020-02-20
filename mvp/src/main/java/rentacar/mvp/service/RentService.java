package rentacar.mvp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import rentacar.mvp.controller.exception.RentacarException;
import rentacar.mvp.controller.rent.request.CreateRentRequest;
import rentacar.mvp.enumeration.RentStatus;
import rentacar.mvp.model.Car;
import rentacar.mvp.model.Rent;
import rentacar.mvp.model.User;
import rentacar.mvp.repository.jpa.CarRepository;
import rentacar.mvp.repository.jpa.RentRepository;
import rentacar.mvp.repository.jpa.UserRepository;

import java.util.Optional;

/**
 * Created by savagaborov on 20.2.2020
 */
@Service
public class RentService {

    private final Logger log = LoggerFactory.getLogger(RentService.class);

    private RentRepository rentRepository;

    private CarRepository carRepository;

    private UserRepository userRepository;

    public RentService (RentRepository rentRepository, CarRepository carRepository, UserRepository userRepository) {
        this.rentRepository = rentRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public void createRent(CreateRentRequest request) {
        log.info("START createRent(request: {})", request);

        Optional<User> borrower = userRepository.findById(request.getStaffId());
        if (!borrower.isPresent()) {
            throw new RentacarException("user.not.exist");
        }

        Optional<User> staff = userRepository.findById(request.getBorrowerId());
        if (!staff.isPresent()) {
            throw new RentacarException("user.not.exist");
        }

        Optional<Car> car = carRepository.getCarByIdAndDeletedIsFalse(request.getCarId());
        if (!car.isPresent()) {
            throw new RentacarException("car.not.exist");
        }

        Rent rent = new Rent();
        rent.setBorrower(borrower.get());
        rent.setStaff(staff.get());
        rent.setCar(car.get());
        rent.setPrice(request.getPrice());
        rent.setRentStatus(RentStatus.ACTIVE);
        rentRepository.save(rent);

        car.get().setAvailable(false);
        carRepository.save(car.get());

        log.info("FINISH createRent()");
    }

    public void finishRent(Long rentId) {
        log.info("START finishRent(rentId: {})", rentId);

        Optional<Rent> rent = rentRepository.getRentByIdAndDeletedFalse(rentId);
        if (!rent.isPresent()) {
            throw new RentacarException("rent.not.exist");
        }

        rent.get().setRentStatus(RentStatus.FINISHED);
        rentRepository.save(rent.get());

        Car car = rent.get().getCar();
        car.setAvailable(true);
        carRepository.save(car);

        log.info("START finishRent()");
    }
}
