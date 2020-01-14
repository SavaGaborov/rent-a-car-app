package rentacar.mvp.model;

import lombok.*;
import rentacar.mvp.enumeration.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by savagaborov on 8.1.20..
 */
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User extends BaseModel {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;
}
