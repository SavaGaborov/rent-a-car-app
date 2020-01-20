package rentacar.mvp.controller.exception;

import org.springframework.http.HttpStatus;

import java.util.Locale;

/**
 * Created by savagaborov on 20.1.2020
 */
public class GeneralRentacarException extends RentacarException {

    public GeneralRentacarException(Locale locale) {
        super("general.exception", locale, HttpStatus.CONFLICT);
    }

    public GeneralRentacarException(Locale locale, HttpStatus httpStatus) {
        super("general.exception", locale, httpStatus);
    }
}
