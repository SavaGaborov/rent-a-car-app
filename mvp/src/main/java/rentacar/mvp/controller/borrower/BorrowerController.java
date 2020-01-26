package rentacar.mvp.controller.borrower;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rentacar.mvp.controller.borrower.request.CreateBorrowerRequest;
import rentacar.mvp.controller.borrower.request.EditBorrowerRequest;
import rentacar.mvp.controller.borrower.response.GetBorrowerResponse;
import rentacar.mvp.controller.borrower.response.GetBorrowerUsersResponse;
import rentacar.mvp.service.BorrowerService;

import javax.validation.Valid;

/**
 * Created by savagaborov on 12.1.2020
 */
@RestController
@RequestMapping("/borrowers")
public class BorrowerController {

    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @ApiOperation(value = "Create borrower user")
    @PostMapping(value="/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public void createBorrowerUser(@RequestBody @Valid CreateBorrowerRequest request) throws Exception {
        borrowerService.createBorrowerUser(request);
    }

    @ApiOperation(value = "Get borrower users")
    @GetMapping(value="/get")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<GetBorrowerUsersResponse> getBorrowerUsers() throws Exception {
        GetBorrowerUsersResponse response = borrowerService.getBorrowerUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Get borrower user")
    @GetMapping(value="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('BORROWER')")
    public ResponseEntity<GetBorrowerResponse> getBorrowerUser(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(borrowerService.getBorrowerUser(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Edit borrower user")
    @PutMapping(value="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('STAFF')")
    public void editBorrowerUser(@PathVariable Long id, @RequestBody @Valid EditBorrowerRequest request) throws Exception {
        borrowerService.editBorrowerUser(id, request);
    }

    @ApiOperation(value = "Delete borrower user")
    @DeleteMapping(value="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public void deleteBorrowerUser(@PathVariable Long id) throws Exception {
        borrowerService.deleteBorrowerUser(id);
    }
}
