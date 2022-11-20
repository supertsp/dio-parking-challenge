package br.com.tiagopedroso.dioparkingchallenge.config;


import br.com.tiagopedroso.dioparkingchallenge.exception.UnauthorizedResourceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler({
            UnauthorizedResourceException.class
    })
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public UnauthorizedResourceException handlingError401(Exception ex, HttpServletRequest request) {
        System.out.println("\n\n\n ExceptionHandlerConfig \n\n\n");
        return (UnauthorizedResourceException)ex;
    }

}
