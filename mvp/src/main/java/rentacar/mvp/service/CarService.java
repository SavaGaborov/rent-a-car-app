package rentacar.mvp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import rentacar.mvp.controller.car.request.AddNewCarRequest;

import javax.validation.Valid;

/**
 * Created by savagaborov on 8.2.2020
 */
@Service
public class CarService {

    private final Logger log = LoggerFactory.getLogger(CarService.class);


    public void addNewCar(@Valid AddNewCarRequest request) {
    }
}
