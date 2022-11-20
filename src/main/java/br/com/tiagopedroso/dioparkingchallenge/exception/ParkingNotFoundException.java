package br.com.tiagopedroso.dioparkingchallenge.exception;

import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ToString(callSuper = true)
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ParkingNotFoundException extends BusinessException {

    public ParkingNotFoundException(String id) {
        super("Parking not found with Id: %s", id);
    }

}
