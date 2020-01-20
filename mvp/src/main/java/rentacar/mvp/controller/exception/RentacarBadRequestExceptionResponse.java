package rentacar.mvp.controller.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by savagaborov on 20.1.2020
 */
public class RentacarBadRequestExceptionResponse {

    @NotBlank
    @JsonProperty("error_description")
    private String errorMessage;

    @NotBlank
    @JsonProperty("error_message")
    private String localizedErrorMessage;

    final static private String VALIDATION_RESOURCE_BUNDLE = "i18n.validation";

    public RentacarBadRequestExceptionResponse(String resourceBundleExceptionKey, Locale locale) {
        Locale defaultLocale = Locale.ENGLISH;
        this.errorMessage = ResourceBundle.getBundle(VALIDATION_RESOURCE_BUNDLE, defaultLocale).getString(resourceBundleExceptionKey);
        this.localizedErrorMessage = ResourceBundle.getBundle(VALIDATION_RESOURCE_BUNDLE, locale != null ? locale : defaultLocale).getString(resourceBundleExceptionKey);
    }

    public RentacarBadRequestExceptionResponse(String resourceBundleExceptionKey, Locale locale, String minValidation, String maxValidation) {
        Locale defaultLocale = Locale.ENGLISH;
        this.errorMessage = ResourceBundle.getBundle(VALIDATION_RESOURCE_BUNDLE, defaultLocale).getString(resourceBundleExceptionKey);
        this.localizedErrorMessage = ResourceBundle.getBundle(VALIDATION_RESOURCE_BUNDLE, locale != null ? locale : defaultLocale).getString(resourceBundleExceptionKey);

        if(StringUtils.hasText(minValidation) && StringUtils.hasText(maxValidation)) {
            this.errorMessage = errorMessage.concat( " (min " ).concat(minValidation).concat(", ").concat("max ").concat(maxValidation).concat(")");
            this.localizedErrorMessage = localizedErrorMessage.concat( " (min " ).concat(minValidation).concat(", ").concat( "max " ).concat(maxValidation).concat(")");
        }
    }
}
