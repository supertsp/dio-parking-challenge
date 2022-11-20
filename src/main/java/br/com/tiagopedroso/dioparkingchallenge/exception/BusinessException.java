package br.com.tiagopedroso.dioparkingchallenge.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ToString(callSuper = true)
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@NoArgsConstructor
public abstract class BusinessException extends RuntimeException {

    @Getter @Setter
    protected LocalDateTime timestamp = LocalDateTime.now();

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Object... args) {
        this(String.format(message, args));
    }

}
