package br.com.tiagopedroso.dioparkingchallenge.exception.handler;

import br.com.tiagopedroso.dioparkingchallenge.exception.UnauthorizedResourceException;
import br.com.tiagopedroso.dioparkingchallenge.tool.HttpResponseWriterHanlder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthExceptionHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {

        final var exception = new UnauthorizedResourceException(request);

        HttpResponseWriterHanlder.write(
                exception,
                request,
                response
        );

        log.error(exception.getMessage());
    }

}