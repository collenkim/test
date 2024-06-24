package com.szs.test.util;

import com.szs.test.dto.JwtPayLoad;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    private String secretKey = "szs1!";

    /**
     * Jwt Access Token 발급
     *
     * @param subject   토큰 제목
     * @param userId   아이디
     * @param expiredDate   토큰 만료일시
     * @return
     */
    public String createAccessToken(String secretKey, String subject, String userId, String expiredDate){

        Key key = getKeyFromBase64EncodedKey(secretKey);
        Date maxExpiredDate = getMaxExpiredDate(expiredDate);

        String expiredDateString = getExpiredDateString(maxExpiredDate);
        JwtPayLoad jwtPayLoad = new JwtPayLoad(userId, expiredDateString);//payload data

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", jwtPayLoad.getUserId());
        claims.put("expiredTime", jwtPayLoad.getExpiredTime());

        return Jwts.builder()
                .subject(subject)
                .claims(claims)
                .issuedAt(new Date())
                .expiration(maxExpiredDate)
                .signWith(key)
                .compact();
    }

    /**
     * Jwt Access Token 재발급
     *
     * @param subject   토큰 제목
     * @param userId   아이디
     * @param expiredDate   토큰 만료일시
     * @return
     */
    public String refreshAccessToken(String secretKey, String subject, String userId, String expiredDate){
        return createAccessToken(secretKey,subject, userId, expiredDate);
    }

    /**
     * Jwt Access Token 파싱
     *
     * @param token
     * @return
     */
    public JwtPayLoad parseAccessToken(String token){
        byte[] secretByte = Base64.getDecoder().decode(secretKey);
        SecretKey key = Keys.hmacShaKeyFor(secretByte);
        Claims payload = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        return new JwtPayLoad((String)payload.get("userId"), (String) payload.get("expiredTime"));//payload data
    }

    /**
     * 인증키 만료 여부 조회
     *
     * @param jwtPayLoad
     * @return
     */
    public boolean isExpiredJwtToken(JwtPayLoad jwtPayLoad){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime expiredDateTime = LocalDateTime.parse(jwtPayLoad.getExpiredTime(), formatter);
        return expiredDateTime.isBefore(LocalDateTime.now());
    }

    public Date getMaxExpiredDate(String expiredDate){
        LocalDate localDate = LocalDate.parse(expiredDate);
        LocalDateTime maxDateTime = localDate.atTime(23, 59, 59);
        Date maxExpiredDate = Date.from(maxDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return maxExpiredDate;
    }

    private String getExpiredDateString(Date expiredDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(expiredDate);
    }

    private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
