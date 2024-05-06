package oauth.oauth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final Key secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public JwtUtil(@Value("${jwt.secret}") String secretString) {
        this.secret = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String name) {
        return Jwts.builder()
                .setSubject(name)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secret)
                .compact();
    }

    public boolean isValidToken(String bearerToken) {
        Claims body = Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(bearerToken)
                .getBody();
        return !body.getExpiration().before(new Date());
    }

    public String getUserName(String bearerToken) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(bearerToken)
                .getBody()
                .getSubject();
    }
}
