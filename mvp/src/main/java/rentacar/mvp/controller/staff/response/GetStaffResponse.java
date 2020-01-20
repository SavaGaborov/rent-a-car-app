package rentacar.mvp.controller.staff.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Created by savagaborov on 20.1.2020
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetStaffResponse {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    private String lastName;
}
