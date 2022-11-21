package br.com.tiagopedroso.dioparkingchallenge.config.security.jwt;

import io.jsonwebtoken.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
public class JwtHandler {

    public static final String ROLES_AUTHORITIES = "authorities";

    public static String createToken(String key, JwtObject jwtObject) {
        final var token = Jwts.builder()
                .setSubject(jwtObject.getSubject())
                .setIssuedAt(jwtObject.getIssuedAtAsDate())
                .setExpiration(jwtObject.getExpirationAsDate())
                .claim(ROLES_AUTHORITIES, adaptRoles(jwtObject.getRoles()))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        return token;
    }

    private static List<String> adaptRoles(List<String> roles) {
        return roles.stream()
                .map(s -> "ROLE_".concat(s.replaceAll("ROLE_", "")))
                .collect(Collectors.toList());
    }

    public static JwtObject convertTokenToObject(String token, String prefix, String key)
            throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException {

        token = token.replace(prefix, "");
        final var claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();

        final var jwtObject = new JwtObject();
        jwtObject.setSubject(claims.getSubject());
        jwtObject.setExpiration(claims.getExpiration());
        jwtObject.setIssuedAt(claims.getIssuedAt());
        jwtObject.setRoles((List) claims.get(ROLES_AUTHORITIES));

        return jwtObject;
    }

}
