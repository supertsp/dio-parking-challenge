package br.com.tiagopedroso.dioparkingchallenge.config.security;

import br.com.tiagopedroso.dioparkingchallenge.config.security.jwt.JwtAuthFilter;
import br.com.tiagopedroso.dioparkingchallenge.exception.handler.JwtAuthExceptionHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static br.com.tiagopedroso.dioparkingchallenge.config.security.SecurityProperties.SWAGGER_WHITELIST;
import static br.com.tiagopedroso.dioparkingchallenge.config.security.SecurityProperties.URIS_WHITELIST;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    /*
    Tips: Spring Security without the (deprecated) WebSecurityConfigurerAdapter
    https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
    https://spring.io/guides/gs/securing-web/
    https://www.bezkoder.com/websecurityconfigureradapter-deprecated-spring-boot/

    Example with JWT Login:   https://www.bezkoder.com/spring-boot-security-login-jwt/
    Example with JWT + MySQL: https://www.bezkoder.com/spring-boot-login-example-mysql/

    TIP for Roles: .antMatchers(HttpMethod.GET,"/users").hasAnyRole("USERS","MANAGERS")
    */

    JwtAuthExceptionHandler jwtAuthExceptionHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors().and().csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .addFilterAfter(new JwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(jwtAuthExceptionHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(requests -> requests
                        .antMatchers(URIS_WHITELIST).permitAll()
                        .antMatchers(SWAGGER_WHITELIST).permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
    }

}
