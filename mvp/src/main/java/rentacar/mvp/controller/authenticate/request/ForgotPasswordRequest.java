package rentacar.mvp.controller.authenticate.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
