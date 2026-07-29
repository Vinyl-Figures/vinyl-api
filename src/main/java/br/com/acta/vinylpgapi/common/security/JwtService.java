package br.com.acta.vinylpgapi.common.security;

import br.com.acta.vinylpgapi.common.config.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtService {
    private final JwtProperties jwtProperties; // store config references
    private final SecretKey key;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;

        // catches String secret and convert to SecretKey, assign every generated token
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    public String generateToken(Long userId){
        return Jwts.builder()
                .subject(userId.toString())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusSeconds(getExpirationSeconds())))
                .signWith(key)
                .compact(); // convert to string
    }

    public long getExpirationSeconds(){
        return jwtProperties.getExpirationMinutes() * 60;
    }

    public Long userIdConverter(String token){
        return Long.valueOf(
                Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(token) // reads the token
                        .getPayload() // get token body
                        .getSubject() // get subject field (user id)
        );
    }
}
