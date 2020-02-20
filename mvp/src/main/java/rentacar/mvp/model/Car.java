package rentacar.mvp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rentacar.mvp.enumeration.CarBrand;
import rentacar.mvp.enumeration.CarType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by savagaborov on 20.1.2020
 */
@Entity
@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Car extends BaseModel {

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "brand")
    private CarBrand carBrand;

    @NotNull
    @Column(name = "model")
    private String model;

    @NotNull
    @Column(name = "type")
    private CarType carType;

    @NotNull
    @Column(name = "registration_number")
    private String registrationNumber;

    @NotNull
    @Column(name = "year_built")
    private String yearBuilt;

    @NotNull
    @Column(name = "available")
    private Boolean available;

    @NotNull
    @Column(name = "times_borrowed")
    private Long timesBorrowed;
}
