package br.com.tiagopedroso.dioparkingchallenge.exception;

import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ToString(callSuper = true)
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ParkingNotFoundException extends BusinessException {

    public static final String MESSAGE = "Parking not found with Id: '%s'";

    public ParkingNotFoundException(String id) {
        super(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                null,
                MESSAGE, id
        );
    }

}
