package br.com.tiagopedroso.dioparkingchallenge.tool;

import br.com.tiagopedroso.dioparkingchallenge.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class HttpResponseWriterHanlder {

    private HttpResponseWriterHanlder(){
        super();
    }

    static final ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public static void write(BusinessException exception, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(exception.getStatus());
        exception.setPathWithRequest(request);

        try {
            mapper.writeValue(response.getOutputStream(), exception.generateResponseBody());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
