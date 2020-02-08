package rentacar.mvp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rentacar.mvp.enumeration.RentStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by savagaborov on 8.2.2020
 */
@Entity
@Table(name = "rents")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Rent extends BaseModel {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private User borrower;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private User staff;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RentStatus rentStatus;

    @Column(name = "price")
    private BigDecimal price;
}
