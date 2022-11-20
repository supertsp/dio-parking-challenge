package br.com.tiagopedroso.dioparkingchallenge.exception;

import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ToString(callSuper = true)
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends BusinessException {

    public UserNotFoundException(Integer id) {
        super("User not found with Id: %d", id);
    }

    public UserNotFoundException(String username) {
        super("User not found with username: %s", username);
    }

}
