package rentacar.mvp.controller.car;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rentacar.mvp.controller.car.request.AddNewCarRequest;
import rentacar.mvp.service.CarService;

import javax.validation.Valid;

/**
 * Created by savagaborov on 8.2.2020
 */
@RestController
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @ApiOperation(value = "Add new car")
    @PostMapping(value="/add")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public void addNewCar(@RequestBody @Valid AddNewCarRequest request) throws Exception {
        carService.addNewCar(request);
    }
}
