package br.com.tiagopedroso.dioparkingchallenge.exception;

import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ToString(callSuper = true)
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class WrongTokenException extends BusinessException {

    public static final String MESSAGE = "Invalid token: %s";

    public WrongTokenException(String token, HttpServletRequest request) {
        super(
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                request,
                MESSAGE, token
        );
    }

}
