package br.com.tiagopedroso.dioparkingchallenge.exception;

import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ToString(callSuper = true)
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class WrongPasswordException extends BusinessException {

    public static final String MESSAGE = "Invalid password for login '%s'";

    public WrongPasswordException(String username) {
        super(
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                null,
                MESSAGE, username
        );
    }

}
