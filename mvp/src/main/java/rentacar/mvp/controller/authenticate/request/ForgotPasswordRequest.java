package rentacar.mvp.controller.authenticate.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static rentacar.mvp.util.ValidationUtil.PASSWORD_PATTERN;

/**
 * Created by savagaborov on 18.1.2020
 */
@Getter
public class ForgotPasswordRequest {

    @Email
    @NotBlank
    @JsonProperty("email")
    private String email;
}
