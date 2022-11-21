package br.com.tiagopedroso.dioparkingchallenge.config;


import br.com.tiagopedroso.dioparkingchallenge.exception.BusinessException;
import br.com.tiagopedroso.dioparkingchallenge.exception.WrongPasswordException;
import br.com.tiagopedroso.dioparkingchallenge.tool.HttpResponseWriterHanlder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerConfig {

    final ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @ExceptionHandler({
            WrongPasswordException.class,
    })
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public void handlingError401(BusinessException ex, HttpServletRequest request, HttpServletResponse response)  {
        log.error(ex.getMessage());

        HttpResponseWriterHanlder.write(
                ex,
                request,
                response
        );
    }

}
