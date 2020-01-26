package rentacar.mvp.controller.borrower.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by savagaborov on 26.1.2020
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetBorrowerUsersResponse {

    @JsonProperty(value = "borrower_users")
    private List<GetBorrowerResponse> borrowerUsers;
}
