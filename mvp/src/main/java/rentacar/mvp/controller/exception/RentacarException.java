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

    public RentacarException(String resourceBundleExceptionKey, HttpStatus httpStatus) {
        this.resourceBundleExceptionKey = resourceBundleExceptionKey;
        this.httpStatus = httpStatus;
        this.locale = Locale.ENGLISH;
    }

    public RentacarException(String resourceBundleExceptionKey) {
        this.resourceBundleExceptionKey = resourceBundleExceptionKey;
        this.httpStatus = HttpStatus.CONFLICT;
        this.locale = Locale.ENGLISH;
    }
}
