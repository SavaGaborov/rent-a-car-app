package rentacar.mvp.controller.authenticate.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static rentacar.mvp.util.ValidationUtil.PASSWORD_PATTERN;

/**
 * Created by savagaborov on 14.1.20..
 */
@Getter
public class ChangePasswordRequest {

    @Email
    @NotBlank
    @JsonProperty("email")
    private String email;

    @NotBlank
    @JsonProperty("old_password")
    @Pattern(regexp = PASSWORD_PATTERN)
    private String oldPassword;

    @NotBlank
    @JsonProperty("new_password")
    @Pattern(regexp = PASSWORD_PATTERN)
    private String newPassword;
}
