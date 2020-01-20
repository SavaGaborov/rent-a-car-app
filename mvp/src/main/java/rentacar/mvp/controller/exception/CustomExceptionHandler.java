package rentacar.mvp.controller.exception;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by savagaborov on 20.1.2020
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler
    public ResponseEntity handleCustomException(RentacarException e) {
        return new ResponseEntity<>(new RentacarExceptionResponse(e.getResourceBundleExceptionKey(), e.getLocale()), e.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
        return new ResponseEntity<>(new RentacarExceptionResponse("general.exception", request.getLocale()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
        return new ResponseEntity<>(new RentacarExceptionResponse("general.exception", request.getLocale()), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
        return new ResponseEntity<>(new RentacarExceptionResponse("general.exception", request.getLocale()), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
        return new ResponseEntity<>(new RentacarExceptionResponse("general.exception", request.getLocale()), HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
        return new ResponseEntity<>(new RentacarExceptionResponse("general.exception", request.getLocale()), HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
        return new ResponseEntity<>(new RentacarExceptionResponse("general.exception", request.getLocale()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
        return new ResponseEntity<>(new RentacarExceptionResponse("general.exception", request.getLocale()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
        return new ResponseEntity<>(new RentacarExceptionResponse("general.exception", request.getLocale()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
        return new ResponseEntity<>(new RentacarExceptionResponse("general.exception", request.getLocale()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
        return new ResponseEntity<>(new RentacarExceptionResponse("general.exception", request.getLocale()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {

        String maxValidation = null;
        String minValidation = null;
        if(ex.getBindingResult().getFieldErrors().get(0).getArguments() != null && ex.getBindingResult().getFieldErrors().get(0).getArguments().length > 1) {
            Object[] fieldArguments = ex.getBindingResult().getFieldErrors().get(0).getArguments();
            if(fieldArguments[1] instanceof Integer && fieldArguments[2] instanceof Integer) {
                maxValidation = fieldArguments[1].toString();
                minValidation = fieldArguments[2].toString();
            }
        }

        final String validationMessageKey = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ResponseEntity<>(new RentacarBadRequestExceptionResponse(validationMessageKey, request.getLocale(), minValidation, maxValidation), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
        return new ResponseEntity<>(new RentacarBadRequestExceptionResponse("general.exception", request.getLocale()), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
        return new ResponseEntity<>(new RentacarBadRequestExceptionResponse("general.exception", request.getLocale()), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
        return new ResponseEntity<>(new RentacarBadRequestExceptionResponse("general.exception", request.getLocale()), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
        return new ResponseEntity<>(new RentacarBadRequestExceptionResponse("general.exception", request.getLocale()), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
        return new ResponseEntity<>(new RentacarBadRequestExceptionResponse("general.exception", request.getLocale()), HttpStatus.BAD_REQUEST);
    }

}
