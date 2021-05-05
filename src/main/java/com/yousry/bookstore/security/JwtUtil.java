package com.yousry.bookstore.security;

import com.yousry.bookstore.dal.domainobject.User;
import com.yousry.bookstore.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtUtil implements Serializable {

    private static final long serialVersionUID = -2550198745626007488L;
    private final String secret = "SMARTDXB";
    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private final long expirationMillSec = 300000;  // 5 min

    @Autowired
    private UserService userService;


    public User validateToken(String token) {
        try {

            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                    .parseClaimsJws(token).getBody();

            String userName = claims.getSubject();
            User user = userService.getUser(userName);

            if (user != null)
                return user;
            else
                return null;

        } catch (Exception e) {
            return null;
        }
    }

    public String generateToken(String name) {

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Claims claims = Jwts.claims().setSubject(name);

        Calendar exp = Calendar.getInstance();
        exp.setTimeInMillis(System.currentTimeMillis() + expirationMillSec);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date()) // now
                .setExpiration(exp.getTime()) // Expiration date
                .signWith(signatureAlgorithm, signingKey)
                .compact();
    }
}