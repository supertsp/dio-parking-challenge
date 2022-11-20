package br.com.tiagopedroso.dioparkingchallenge.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthExceptionHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(UnauthorizedResourceException.STATUS);

        final var exception = new UnauthorizedResourceException(request);
        final var mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), exception.generateResponseBody());

        //Is this necessary for logs? 🤔
        throw new UnauthorizedResourceException();
    }

}