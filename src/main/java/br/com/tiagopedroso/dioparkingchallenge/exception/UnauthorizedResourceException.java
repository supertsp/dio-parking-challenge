package br.com.tiagopedroso.dioparkingchallenge.exception;

import br.com.tiagopedroso.dioparkingchallenge.tool.OrderedMapHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ToString(callSuper = true)
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthorizedResourceException extends BusinessException {

    public static final int STATUS = HttpStatus.UNAUTHORIZED.value();
    public static final String ERROR = "Unauthorized";
    public static final String MESSAGE = "Unauthorized resource due to lack of authentication";

    @Getter
    @Setter
    String path = "";

    public UnauthorizedResourceException() {
        super(MESSAGE);
    }

    public UnauthorizedResourceException(HttpServletRequest request) {
        super(MESSAGE);
        this.path = request == null ? "" : request.getRequestURI();
    }

    public Map<String, Object> generateResponseBody() {
        return OrderedMapHandler.create(
                "timestamp", timestamp.toString(),
                "status", STATUS,
                "error", ERROR,
                "message", MESSAGE,
                "path", path
        );
    }

}
