package rentacar.mvp.controller.rent;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rentacar.mvp.controller.rent.request.CreateRentRequest;
import rentacar.mvp.service.RentService;

import javax.validation.Valid;

/**
 * Created by savagaborov on 20.2.2020
 */
@RestController
@RequestMapping("/rents")
public class RentController {

    private RentService rentService;

    public RentController (RentService rentService) {this.rentService = rentService;}

    @ApiOperation(value = "Create rent")
    @PostMapping(value="/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('STAFF')")
    public void createRent(@RequestBody @Valid CreateRentRequest request) throws Exception {
        rentService.createRent(request);
    }

    @ApiOperation(value = "Finish rent")
    @PostMapping(value="/{id}/finish")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('STAFF')")
    public void finishRent(@PathVariable Long id) throws Exception {
        rentService.finishRent(id);
    }
}
