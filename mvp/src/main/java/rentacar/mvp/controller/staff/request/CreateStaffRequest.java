package rentacar.mvp.controller.staff.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static rentacar.mvp.util.ValidationUtil.PASSWORD_PATTERN;

/**
 * Created by savagaborov on 12.1.2020
 */
@Getter
public class CreateStaffRequest {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @Email
    @NotBlank
    @JsonProperty("email")
    private String email;

    @NotBlank
    @JsonProperty("password")
    @Pattern(regexp = PASSWORD_PATTERN)
    private String password;

    @NotBlank
    @JsonProperty("phone_number")
    private String phoneNumber;
}
