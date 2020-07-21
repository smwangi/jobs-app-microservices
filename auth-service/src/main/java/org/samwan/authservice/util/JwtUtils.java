package org.samwan.authservice.util;

import io.jsonwebtoken.*;
import org.samwan.authservice.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${authserver.app.jwtsecret}")
    private String jwtSecret;

    @Value("${authserver.app.jwtExpiration}")
    private String jwtExpiration;

    public String generateJwtToken(Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();

        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());

        return Jwts.builder()
                .setExpiration(Date.from(zonedDateTime.plusMinutes(30).toInstant()))
                .compact();
    }

    public String getUsernameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }catch (SignatureException e){
            LOGGER.error("Invalid JWT Signature: {}",e.getMessage());
        }catch (MalformedJwtException e){
            LOGGER.error("Invalid JWT Token: {} ",e.getMessage());
        }catch (ExpiredJwtException e){
            LOGGER.error("JWT token is expired: {}",e.getMessage());
        }catch (UnsupportedJwtException e){
            LOGGER.error("JWT Token is unsupported {} ",e.getMessage());
        }catch (IllegalArgumentException e){
            LOGGER.error("JWT claims string is empty: {} ");
        }
        return false;
    }
}
