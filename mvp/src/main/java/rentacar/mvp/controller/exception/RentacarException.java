package rentacar.mvp.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Locale;

/**
 * Created by savagaborov on 20.1.2020
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RentacarException extends RuntimeException {

    private String resourceBundleExceptionKey;

    private Locale locale;

    private HttpStatus httpStatus;
}
