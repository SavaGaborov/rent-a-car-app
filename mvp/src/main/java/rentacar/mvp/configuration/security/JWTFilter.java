package rentacar.mvp.configuration.security;


import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static rentacar.mvp.configuration.security.JWTUtil.*;

/**
 * Created by savagaborov on 16.1.2020
 */
public class JWTFilter extends GenericFilterBean {

    private final Logger log = LoggerFactory.getLogger(JWTFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        final HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        final Optional<String> jwtToken = extractToken(httpServletRequest);
        try {
            if (jwtToken.isPresent()) {
                final Authentication authentication = JWTUtil.getAuthentication(jwtToken.get(), ACCESS_SECRET_KEY);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            if(jwtToken.isPresent()) {
                try {
                    final Authentication authentication = JWTUtil.getAuthentication(jwtToken.get(), REFRESH_SECRET_KEY);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(servletRequest, servletResponse);
                } catch (SignatureException e1) {
                    log.debug("Authentication token is expired! - {}", e.getMessage());
//                    returnUnauthorizedResponse(AUTHENTICATION_TOKEN_IS_EXPIRED, httpServletResponse, new ExceptionDataDTO(TOKEN_EXPIRED, null));
                } catch (Exception e2) {
                    log.debug("Authentication token is invalid! - {}", e.getMessage());
//                    returnUnauthorizedResponse(AUTHENTICATION_TOKEN_IS_INVALID, httpServletResponse, null);
                }
            }
        }
    }

    private Optional<String> extractToken(HttpServletRequest request) {
        final String bearerToken = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER)) {
            final String jwtToken = bearerToken.substring(BEARER.length(), bearerToken.length());
            return Optional.of(jwtToken);
        }
        return Optional.empty();
    }

    public static String extractRefreshToken(String refreshToken) throws Exception {
        if (refreshToken.startsWith(BEARER)) {
            return refreshToken.substring(BEARER.length(), refreshToken.length());
        }
//        throw new Exception("invalid.token", HttpStatus.UNAUTHORIZED);
        throw new Exception("invalid.token");
    }
}
