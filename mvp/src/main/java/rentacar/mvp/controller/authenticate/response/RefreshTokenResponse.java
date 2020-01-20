package rentacar.mvp.controller.authenticate.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by savagaborov on 20.1.2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenResponse {

    private Long userId;

    private String accessToken;

    private String refreshToken;

    private Long validityInMinutes;
}
