package rentacar.mvp.controller.borrower.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Created by savagaborov on 26.1.2020
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetBorrowerResponse {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    private String lastName;

    @JsonProperty(value = "deleted")
    private Boolean deleted;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "phone_number")
    private String phone_number;
}
