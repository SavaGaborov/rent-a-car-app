package rentacar.mvp.configuration.security;

import rentacar.mvp.model.User;
import rentacar.mvp.enumeration.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;

/**
 * Created by savagaborov on 16.1.2020
 */
public final class JWTUtil {

    public static final long ACCESS_TOKEN_VALIDITY_IN_MINUTES = 60*24;
    public static final long REFRESH_TOKEN_VALIDITY_IN_MINUTES = 60*24*30;
    private static final String AUTHORITIES_KEY = "authkey";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public final static String ACCESS_SECRET_KEY = "rentacaraccesstokensecretkey";
    public final static String REFRESH_SECRET_KEY = "rentacarrefreshtokensecretkey";

    public static String generateAccessToken(User user) {
        return createToken(user.getId(), user.getRole(), ZonedDateTime.now(ZoneId.of("UTC")).plusMinutes(ACCESS_TOKEN_VALIDITY_IN_MINUTES), ACCESS_SECRET_KEY);
    }

    public static String generateRefreshToken(User user) {
        return createToken(user.getId(), user.getRole(), ZonedDateTime.now(ZoneId.of("UTC")).plusMinutes(REFRESH_TOKEN_VALIDITY_IN_MINUTES), REFRESH_SECRET_KEY);
    }

    public static String createToken(Long userId, Role role, ZonedDateTime validity, String secretKey) {
        return Jwts.builder().setSubject(userId.toString()).claim(AUTHORITIES_KEY, role.name()).signWith(SignatureAlgorithm.HS512, secretKey).setExpiration(Date.from(validity.toInstant())).compact();
    }

    public static Authentication getAuthentication(String token, String secretKey) {

        final Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        final Long userId = Long.valueOf(claims.getSubject());
        final String role = claims.get(AUTHORITIES_KEY).toString();

        return new PreAuthenticatedAuthenticationToken(userId, null, Collections.singletonList(new SimpleGrantedAuthority(role)));
    }
}
