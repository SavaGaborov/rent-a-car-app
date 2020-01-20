package rentacar.mvp.controller.staff;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rentacar.mvp.controller.staff.request.CreateStaffRequest;
import rentacar.mvp.service.StaffService;

import javax.validation.Valid;

/**
 * Created by savagaborov on 12.1.2020
 */
@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @ApiOperation(value = "Create staff user")
    @PostMapping(value="/staff/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public void createStaffUser(@RequestBody @Valid CreateStaffRequest request) throws Exception {
        staffService.createStaffUser(request);
    }
}
