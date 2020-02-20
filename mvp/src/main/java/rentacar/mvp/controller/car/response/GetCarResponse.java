package rentacar.mvp.controller.car.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import rentacar.mvp.enumeration.CarBrand;
import rentacar.mvp.enumeration.CarType;

/**
 * Created by savagaborov on 9.2.2020
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCarResponse {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "brand")
    private CarBrand carBrand;

    @JsonProperty(value = "model")
    private String model;

    @JsonProperty(value = "type")
    private CarType type;

    @JsonProperty(value = "registration_number")
    private String registrationNumber;

    @JsonProperty(value = "year_built")
    private String yearBuilt;

    @JsonProperty(value = "times_borrowed")
    private Long timesBorrowed;
}
