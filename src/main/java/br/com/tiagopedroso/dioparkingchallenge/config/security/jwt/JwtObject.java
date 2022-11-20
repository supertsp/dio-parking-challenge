package br.com.tiagopedroso.dioparkingchallenge.config.security.jwt;

import br.com.tiagopedroso.dioparkingchallenge.tool.DateTimeConverter;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtObject {

    String subject; //Username
    LocalDateTime issuedAt; //Token Creation Date
    LocalDateTime expiration; //Token Expiration Date
    @EqualsAndHashCode.Exclude
    List<String> roles; //Access Profiles

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = DateTimeConverter.toLocalDateTime(issuedAt);
    }

    public Date getIssuedAtAsDate() {
        return DateTimeConverter.toDate(issuedAt);
    }

     public void setExpiration(LocalDateTime expiration) {
        this.issuedAt = expiration;
    }

    public void setExpiration(Date expiration) {
        this.issuedAt = DateTimeConverter.toLocalDateTime(expiration);
    }

    public Date getExpirationAsDate() {
        return DateTimeConverter.toDate(expiration);
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void setRoles(String... roles) {
        this.roles = Arrays.asList(roles);
    }

}