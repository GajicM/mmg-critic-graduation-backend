package raf.diplomski.mmgcritic.jwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import raf.diplomski.mmgcritic.data.dto.UserDto;
import raf.diplomski.mmgcritic.data.entities.user.User;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "jwt-token-secret-key";

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Long extractUserId(String token) {
        return extractAllClaims(token).get("id", Long.class);
    }

    public String extractUserRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public List<String> extractPermissions(String token) {
        Claims claims = extractAllClaims(token);
        List<?> rawList = claims.get("permissions", List.class);
        return rawList.stream()
                .map(object -> String.valueOf(object))
                .collect(Collectors.toList());
    }

    public String generateToken(UserDto userDto) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userDto.getId());
        claims.put("email", userDto.getEmail());
        claims.put("username",userDto.getUsername());
        claims.put("role", userDto.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDto.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }
}
