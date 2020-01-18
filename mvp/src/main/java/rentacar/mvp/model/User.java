package rentacar.mvp.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import rentacar.mvp.enumeration.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

/**
 * Created by savagaborov on 8.1.2020
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

    @Column(name = "reset_password_code")
    private String resetPasswordCode;

    @Column(name = "reset_password_code_timestamp")
    private ZonedDateTime resetPasswordCodeTimestamp;
}
