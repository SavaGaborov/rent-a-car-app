package rentacar.mvp.controller.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by savagaborov on 20.1.2020
 */
public class RentacarExceptionResponse {

    @NotBlank
    @JsonProperty("error_description")
    private String errorMessage;

    @NotBlank
    @JsonProperty("error_message")
    private String localizedErrorMessage;

    final static private String EXCEPTION_RESOURCE_BUNDLE = "i18n.exception";

    public RentacarExceptionResponse(String resourceBundleExceptionKey, Locale locale) {
        Locale defaultLocale = Locale.ENGLISH;
        this.errorMessage = ResourceBundle.getBundle(EXCEPTION_RESOURCE_BUNDLE, defaultLocale).getString(resourceBundleExceptionKey);
        this.localizedErrorMessage = ResourceBundle.getBundle(EXCEPTION_RESOURCE_BUNDLE, locale != null ? locale : defaultLocale).getString(resourceBundleExceptionKey);
    }
}
