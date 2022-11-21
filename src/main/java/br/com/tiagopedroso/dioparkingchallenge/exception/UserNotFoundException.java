package br.com.tiagopedroso.dioparkingchallenge.exception;

import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ToString(callSuper = true)
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends BusinessException {

    public static final String MESSAGE_ID = "User not found with Id: %d";
    public static final String MESSAGE_USERNAME = "User not found with username: %s";
    public static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

    public UserNotFoundException(Integer id) {
        super(
                HTTP_STATUS.value(),
                HTTP_STATUS.getReasonPhrase(),
                null,
                MESSAGE_ID, id
        );
    }

    public UserNotFoundException(String username) {
        super(
                HTTP_STATUS.value(),
                HTTP_STATUS.getReasonPhrase(),
                null,
                MESSAGE_USERNAME, username
        );
    }

}
