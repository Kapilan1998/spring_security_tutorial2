package com.spring.security.spring.security.practical.config;

import com.spring.security.spring.security.practical.entity.BaseUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security-expiration-in-minutes}")
    private long expirationInMinutes;

    @Value("${secret-key}")
    private String secretKey;       // got from base64 encode website
    public String generateToken(BaseUser baseUser, Map<String, Object> extraClaims) {

        Date issuedat = new Date(System.currentTimeMillis());
        Date expiresAt = new Date(issuedat.getTime() + (expirationInMinutes*60*1000));
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(baseUser.getName())
                .setIssuedAt(issuedat)
                .setExpiration(expiresAt)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    private Key generateKey(){
        byte[] keyInformatory = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyInformatory);
    }

    public String extractUserName(String jwt) {
//      return  Jwts.parserBuilder().setSigningKey(generateKey()).build()
//                 .parseClaimsJws(jwt).getBody().getSubject();

        return  Jwts.parser().setSigningKey(generateKey()).build().parseClaimsJws(jwt).getBody().getSubject();
    }



    //55.10
}
