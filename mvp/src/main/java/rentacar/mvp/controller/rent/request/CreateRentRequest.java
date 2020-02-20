package rentacar.mvp.controller.rent.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by savagaborov on 20.2.2020
 */
@Getter
public class CreateRentRequest {

    @NotNull
    @JsonProperty("car_id")
    private Long carId;

    @NotNull
    @JsonProperty("borrower_id")
    private Long borrowerId;

    @NotNull
    @JsonProperty("staff_id")
    private Long staffId;

    @NotNull
    @JsonProperty("price")
    private BigDecimal price;

}
