package rentacar.mvp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
    @Column(name = "brand")
    private String brand;

    @NotNull
    @Column(name = "model")
    private String model;

    @NotNull
    @Column(name = "type")
    private String type;

    @NotNull
    @Column(name = "registration_number")
    private String registration_number;

    @NotNull
    @Column(name = "year_built")
    private String year_built;

    @NotNull
    @Column(name = "available")
    private Boolean available;

    @NotNull
    @Column(name = "times_borrowed")
    private Long times_borrowed;
}
