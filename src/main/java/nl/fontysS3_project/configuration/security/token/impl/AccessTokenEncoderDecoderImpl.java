package nl.fontysS3_project.configuration.security.token.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import nl.fontysS3_project.configuration.security.token.AccessToken;
import nl.fontysS3_project.configuration.security.token.AccessTokenDecoder;
import nl.fontysS3_project.configuration.security.token.AccessTokenEncoder;
import nl.fontysS3_project.configuration.security.token.exception.InvalidAccessTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccessTokenEncoderDecoderImpl implements AccessTokenEncoder, AccessTokenDecoder {
    private final Key key;
    public static final String CLAIM_PERMISSION = "permission";
    public static final String CLAIM_USER_ID = "userId";

    public AccessTokenEncoderDecoderImpl(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String encode(AccessToken accessToken) {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put(CLAIM_PERMISSION, accessToken.getPermission());

        if (accessToken.getUserId() != null) {
            claimsMap.put(CLAIM_USER_ID, accessToken.getUserId());
        }

        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(accessToken.getSubject())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .addClaims(claimsMap)
                .signWith(key)
                .compact();
    }

    @Override
    public AccessToken decode(String accessTokenEncoded) {
        try {
            Jwt<?, Claims> jwt = Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(accessTokenEncoded);
            Claims claims = jwt.getBody();

            Long userId = claims.get(CLAIM_USER_ID, Long.class);
            int permission = claims.get(CLAIM_PERMISSION, Integer.class);

            return new AccessTokenImpl(claims.getSubject(), userId, permission);
        } catch (JwtException e) {
            throw new InvalidAccessTokenException(e.getMessage());
        }
    }
}
