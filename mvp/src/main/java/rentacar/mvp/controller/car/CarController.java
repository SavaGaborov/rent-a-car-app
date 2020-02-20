package rentacar.mvp.controller.car;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rentacar.mvp.controller.car.request.AddNewCarRequest;
import rentacar.mvp.controller.car.response.GetCarResponse;
import rentacar.mvp.controller.car.response.GetCarsResponse;
import rentacar.mvp.enumeration.CarBrand;
import rentacar.mvp.enumeration.CarType;
import rentacar.mvp.model.Car;
import rentacar.mvp.service.CarService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static rentacar.mvp.controller.Converter.toGetCarResponse;

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

    @ApiOperation(value = "Get car")
    @GetMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('BORROWER') or hasAuthority('STAFF')")
    public ResponseEntity<GetCarResponse> getCar(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(carService.getCar(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete car")
    @DeleteMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public void deleteCar(@PathVariable Long id) throws Exception {
        carService.deleteCar(id);
    }

    @ApiOperation(value = "Update out of order car")
    @DeleteMapping(value="/out-of-order/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('STAFF')")
    public void outOfOrderCar(@PathVariable Long id) throws Exception {
        carService.outOfOrderCar(id);
    }

    @ApiOperation(value = "Get available cars")
    @GetMapping(value="/available")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('BORROWER') or hasAuthority('STAFF')")
    public ResponseEntity<GetCarsResponse> getAvailableCars(@RequestParam CarBrand carBrand, @RequestParam CarType carType) throws Exception {
        List<Car> availableCars = carService.getAvailableCars(carBrand, carType);
        return ResponseEntity.ok(new GetCarsResponse(availableCars.stream().map(car -> toGetCarResponse(car)).collect(Collectors.toList())));
    }

}
