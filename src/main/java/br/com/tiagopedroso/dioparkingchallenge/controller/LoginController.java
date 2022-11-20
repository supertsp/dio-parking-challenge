package br.com.tiagopedroso.dioparkingchallenge.controller;

import br.com.tiagopedroso.dioparkingchallenge.config.security.SecurityConfig;
import br.com.tiagopedroso.dioparkingchallenge.config.security.SecurityProperties;
import br.com.tiagopedroso.dioparkingchallenge.config.security.jwt.JwtHandler;
import br.com.tiagopedroso.dioparkingchallenge.config.security.jwt.JwtObject;
import br.com.tiagopedroso.dioparkingchallenge.controller.dto.LoginDto;
import br.com.tiagopedroso.dioparkingchallenge.controller.dto.SessionDto;
import br.com.tiagopedroso.dioparkingchallenge.exception.WrongPasswordException;
import br.com.tiagopedroso.dioparkingchallenge.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static br.com.tiagopedroso.dioparkingchallenge.config.ApiUrl.BASE_URI;

@RestController
@AllArgsConstructor
@RequestMapping(BASE_URI + "/login")
@Tag(name = "Login", description = "API login operations to obtain JWT token")
public class LoginController {

    private PasswordEncoder encoder;
    private SecurityConfig securityConfig;
    private UserService service;

    @PostMapping
    public SessionDto signIn(@RequestBody LoginDto login) {
        final var user = service.findByUsername(login.getUsername());
        final var passwordOk = encoder.matches(login.getPassword(), user.getPassword());

        if (!passwordOk) {
            throw new WrongPasswordException(login.getUsername());
        }

        final var jwtObject = JwtObject
                .builder()
                .issuedAt(LocalDateTime.now())
                .expiration(LocalDateTime.now().plusSeconds(SecurityProperties.EXPIRATION))
                .roles(user.getRoles())
                .build();

        return SessionDto
                .builder()
                .username(user.getUsername())
                .token(
                        JwtHandler.createToken(
                                SecurityProperties.PREFIX,
                                SecurityProperties.KEY,
                                jwtObject
                        )
                )
                .build();
    }

}
