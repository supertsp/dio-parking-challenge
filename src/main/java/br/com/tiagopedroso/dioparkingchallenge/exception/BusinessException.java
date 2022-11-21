package br.com.tiagopedroso.dioparkingchallenge.exception;

import br.com.tiagopedroso.dioparkingchallenge.tool.OrderedMapHandler;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;

@ToString(callSuper = true)
@Getter
@Setter
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@NoArgsConstructor
@JsonIgnoreProperties({"cause", "localizedMessage", "stackTrace", "suppressed"})
public abstract class BusinessException extends RuntimeException {

    protected LocalDateTime timestamp = LocalDateTime.now();
    protected int status;
    protected String error;
    protected String path;

    public BusinessException(String message, int status, String error, HttpServletRequest request) {
        super(message);
        this.setStatus(status);
        this.error = error;
        this.setPathWithRequest(request);
    }

    public BusinessException(int status, String error, HttpServletRequest request, String message, Object... args) {
        this(String.format(message, args), status, error, request);
    }

    public void setStatus(int status) {
        if (status >= 400 && status <= 599) {
            this.status = status;
        }
    }

    public void setPathWithRequest(HttpServletRequest request){
        this.path = request == null ? "" : request.getRequestURI();
    }

    @JsonGetter("timestamp")
    public String getTimestampString() {
        return timestamp.toString();
    }

    public Map<String, Object> generateResponseBody() {
        return OrderedMapHandler.create(
                "timestamp", timestamp.toString(),
                "status", status,
                "error", error,
                "message", this.getMessage(),
                "path", path
        );
    }

}
