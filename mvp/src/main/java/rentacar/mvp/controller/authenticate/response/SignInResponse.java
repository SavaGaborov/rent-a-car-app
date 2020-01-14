package rentacar.mvp.controller.authenticate.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import rentacar.mvp.enumeration.Role;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by savagaborov on 14.1.20..
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponse {

    @Email
    @NotBlank
    @JsonProperty("email")
    private String email;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @NotNull
    @JsonProperty("role")
    private Role role;

    @NotNull
    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotBlank
    @JsonProperty("access_token")
    private String accessToken;

    @NotBlank
    @JsonProperty("refresh_token")
    private String refreshToken;
}
