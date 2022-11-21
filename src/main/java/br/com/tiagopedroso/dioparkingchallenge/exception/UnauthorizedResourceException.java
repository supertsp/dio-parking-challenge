package br.com.tiagopedroso.dioparkingchallenge.exception;

import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ToString(callSuper = true)
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthorizedResourceException extends BusinessException {

    public static final String MESSAGE = "Unauthorized resource due to lack of authentication";
    public static final HttpStatus HTTP_STATUS = HttpStatus.UNAUTHORIZED;

    public UnauthorizedResourceException() {
        super(
                MESSAGE,
                HTTP_STATUS.value(),
                HTTP_STATUS.getReasonPhrase(),
                null
        );
    }

    public UnauthorizedResourceException(HttpServletRequest request) {
        super(
                MESSAGE,
                HTTP_STATUS.value(),
                HTTP_STATUS.getReasonPhrase(),
                request
        );
    }

}
