package com.kyonggi.cspop.support;

import com.kyonggi.cspop.exception.InvalidTokenException;
import com.kyonggi.cspop.exception.TokenInvalidExpiredException;
import com.kyonggi.cspop.exception.TokenInvalidFormException;
import com.kyonggi.cspop.exception.TokenInvalidSecretKeyException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenProvider {

    private final SecretKey key;
    private final long validityInMilliseconds;

    public JwtTokenProvider(@Value("${jwt.token.secret-key}") final String secretKey,
                            @Value("${jwt.token.expire-length}") final long validityInMilliseconds) {

        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public String createToken(Map<String, Object> payload) {
        Claims claims = Jwts.claims(payload);
        final Date now = new Date();
        final Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getPayload(final String token) {
        return tokenToJws(token)
                .getBody()
                .getSubject();
    }

    public String getRolePayload(final String token) {
        return tokenToJws(token)
                .getBody()
                .get("role")
                .toString();
    }

    public void validateAbleToken(final String token) {
        try {
            final Jws<Claims> claims = tokenToJws(token);

            validateExpiredToken(claims);
        } catch (final JwtException | InvalidTokenException e) {
            throw new TokenInvalidSecretKeyException(token);
        }
    }

    private Jws<Claims> tokenToJws(final String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
        } catch (final IllegalArgumentException | MalformedJwtException e) {
            throw new TokenInvalidFormException();
        } catch (final SignatureException e) {
            throw new TokenInvalidSecretKeyException(token);
        } catch (final ExpiredJwtException e) {
            throw new TokenInvalidExpiredException();
        }
    }

    private void validateExpiredToken(final Jws<Claims> claims) {
        if (claims.getBody().getExpiration().before(new Date())) {
            throw new TokenInvalidExpiredException();
        }
    }

    public static PayloadBuilder payloadBuilder() {
        return new PayloadBuilder();
    }

    public static class PayloadBuilder {
        private final Claims claims;

        private PayloadBuilder() {
            this.claims = Jwts.claims();
        }

        public PayloadBuilder setSubject(String subject) {
            claims.setSubject(subject);
            return this;
        }

        public PayloadBuilder put(String payload) {
            claims.put("role", payload);
            return this;
        }

        public Map<String, Object> build() {
            return claims;
        }
    }
}
