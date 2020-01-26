package rentacar.mvp.controller.staff;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rentacar.mvp.controller.staff.request.CreateStaffRequest;
import rentacar.mvp.controller.staff.request.EditStaffRequest;
import rentacar.mvp.controller.staff.response.GetStaffResponse;
import rentacar.mvp.controller.staff.response.GetStaffUsersResponse;
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
    @PostMapping(value="/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public void createStaffUser(@RequestBody @Valid CreateStaffRequest request) throws Exception {
        staffService.createStaffUser(request);
    }

    @ApiOperation(value = "Get staff users")
    @GetMapping(value="/get")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<GetStaffUsersResponse> getStaffUsers() throws Exception {
        GetStaffUsersResponse response = staffService.getStaffUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Get staff user")
    @GetMapping(value="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('STAFF')")
    public ResponseEntity<GetStaffResponse> getStaffUser(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(staffService.getStaffUser(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Edit staff user")
    @PutMapping(value="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('STAFF')")
    public void editStaffUser(@PathVariable Long id, @RequestBody @Valid EditStaffRequest request) throws Exception {
        staffService.editStaffUser(id, request);
    }

    @ApiOperation(value = "Delete staff user")
    @DeleteMapping(value="/staff/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public void deleteStaffUser(@PathVariable Long id) throws Exception {
        staffService.deleteStaffUser(id);
    }
}
