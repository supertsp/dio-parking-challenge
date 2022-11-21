package br.com.tiagopedroso.dioparkingchallenge.exception;

import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ToString(callSuper = true)
@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class ForbiddenResourceException extends BusinessException {
    public static final String MESSAGE = "Forbidden resource for role '%s'";

    public ForbiddenResourceException(String role) {
        super(
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                null,
                MESSAGE, role
        );
    }

}
