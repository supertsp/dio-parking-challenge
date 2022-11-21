package br.com.tiagopedroso.dioparkingchallenge.config.security.jwt;

import br.com.tiagopedroso.dioparkingchallenge.config.security.SecurityProperties;
import br.com.tiagopedroso.dioparkingchallenge.exception.WrongTokenException;
import br.com.tiagopedroso.dioparkingchallenge.tool.HttpResponseWriterHanlder;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    public static final String HEADER_AUTHORIZATION = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader(HEADER_AUTHORIZATION);

        //validating token integrity
        try {
            if (token != null && !token.isEmpty()) {
                final var jwtObject = JwtHandler.convertTokenToObject(
                        token,
                        SecurityProperties.PREFIX,
                        SecurityProperties.KEY
                );

                final var authorities = convertRolesToAuthorities(jwtObject.getRoles());

                final var userToken = new UsernamePasswordAuthenticationToken(
                        jwtObject.getSubject(),
                        null,
                        authorities);

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(userToken);

            } else {
                SecurityContextHolder.clearContext();
            }

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException e) {

            final var exception = new WrongTokenException(token, request);

            HttpResponseWriterHanlder.write(
                    exception,
                    request,
                    response
            );

            log.error(exception.getMessage());
        }
    }

    private List<SimpleGrantedAuthority> convertRolesToAuthorities(List<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}