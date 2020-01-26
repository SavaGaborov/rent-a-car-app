package rentacar.mvp.controller.staff.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by savagaborov on 20.1.2020
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetStaffUsersResponse {

    @JsonProperty(value = "staff_users")
    private List<GetStaffResponse> staffUsers;
}
