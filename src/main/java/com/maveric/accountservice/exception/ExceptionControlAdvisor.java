package com.maveric.accountservice.exception;

import com.maveric.accountservice.dto.ErrorDto;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import static com.maveric.accountservice.constants.Constants.*;

@RestControllerAdvice
public class ExceptionControlAdvisor {
    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ErrorDto handleAccountNotFoundException(AccountNotFoundException exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(ACCOUNT_NOT_FOUND_CODE);
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }
    //Customer Id missmatch
    @ExceptionHandler(CustomerIdMissmatch.class)
    public final ErrorDto handleCustomerIdMismatchException(CustomerIdMissmatch customerIdMissmatch) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorDto.setMessage(customerIdMissmatch.getMessage());
        return errorDto;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ErrorDto> handleMessageNotReadableException() {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorDto.setMessage("Type should be either 'CURRENT' or 'SAVINGS'");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errorDto);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(BAD_REQUEST_CODE);
        errorDto.setMessage(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return errorDto;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorDto handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(METHOD_NOT_ALLOWED_CODE);
        errorDto.setMessage(METHOD_NOT_ALLOWED_MESSAGE);
        return errorDto;
    }

    @ExceptionHandler(BalanceFeignException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorDto handleBalanceFeignException(BalanceFeignException balanceFeignException) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(ACCOUNT_NOT_FOUND_CODE);
        errorDto.setMessage(balanceFeignException.getMessage());
        return errorDto;
    }

    @ExceptionHandler(FeignException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorDto feignExceptionError(FeignException exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(ACCOUNT_NOT_FOUND_CODE);
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }
}
