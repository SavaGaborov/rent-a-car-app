package rentacar.mvp.controller.car.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import rentacar.mvp.enumeration.CarBrand;
import rentacar.mvp.enumeration.CarType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by savagaborov on 8.2.2020
 */
@Getter
public class AddNewCarRequest {

    @NotBlank
    @JsonProperty("car_brand")
    private CarBrand carBrand;

    @NotBlank
    @JsonProperty("model")
    private String model;

    @NotBlank
    @JsonProperty("car_type")
    private CarType carType;

    @NotBlank
    @JsonProperty("registration_number")
    private String registration_number;

    @NotBlank
    @JsonProperty("year_built")
    private String year_built;

    @NotNull
    @JsonProperty("available")
    private Boolean available;
}
