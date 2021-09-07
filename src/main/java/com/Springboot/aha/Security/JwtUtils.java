package com.Springboot.aha.Security;

import com.Springboot.aha.Service.impl.UserDetailsImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtUtils {
    @Value("${aha.app.jwtExpirationMs}")
    private int expiredTime;

    @Value("${aha.app.sercretKey}")
    private String SecretKey;

   public static final String Bearer="Bearer ";

    public boolean validToken(String token) {
        try {
            Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error("invalid token");
            return false;
        }
    }

    public String generateToken(UserDetailsImpl userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername(), userDetails);
    }

    private String createToken(Map<String, Object> claims, String subject, UserDetailsImpl user) {
        Algorithm algorithm = algorithm(SecretKey);
        return JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiredTime))
                .withIssuedAt(new Date())
                .withClaim("id", user.getId())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(token).getBody().getSubject();
    }


    private DecodedJWT decodedJWT(String token) {
        Algorithm algorithm = algorithm(SecretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        return decodedJWT;
    }

    public String getUserName(String token) {
        return decodedJWT(token).getSubject();
    }

    public String[] getRoles(String token) {
        return decodedJWT(token).getClaim("roles").asArray(String.class);
    }

    public int getId(String token) {
        DecodedJWT decodedJWT = decodedJWT(token);
        return decodedJWT.getClaim("id").as(Integer.class);
    }

    private Algorithm algorithm(String secretKey) {
        return Algorithm.HMAC256(secretKey);
    }
}
