package rentacar.mvp.controller.authenticate.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Created by savagaborov on 14.1.20..
 */
@Getter
public class SignInRequest {

    @Email
    @NotBlank
    @JsonProperty("email")
    private String email;

    @NotBlank
    @JsonProperty("password")
    private String password;
}
