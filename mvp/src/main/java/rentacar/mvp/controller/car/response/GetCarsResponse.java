package rentacar.mvp.controller.car.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by savagaborov on 20.2.2020
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCarsResponse {

    @JsonProperty(value = "available_cars")
    private List<GetCarResponse> availableCars;
}
