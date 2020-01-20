package rentacar.mvp.controller.authenticate.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static rentacar.mvp.util.ValidationUtil.PASSWORD_PATTERN;

/**
 * Created by savagaborov on 20.1.2020
 */
@Getter
public class ResetPasswordRequest {

    @Email
    @NotBlank
    @JsonProperty("email")
    private String email;

    @NotBlank
    @JsonProperty("password")
    @Pattern(regexp = PASSWORD_PATTERN)
    private String password;
}
