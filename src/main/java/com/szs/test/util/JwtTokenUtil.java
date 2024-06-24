package com.szs.test.util;

import com.szs.test.constants.Constants;
import com.szs.test.dto.JwtPayLoad;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    @Resource
    private SecretKeyUtil secretKeyUtil;

    /**
     * Jwt Access Token 발급
     *
     * @param subject   토큰 제목
     * @param userId   아이디
     * @param expiredDateTime   토큰 만료일시
     * @return
     */
    public String createAccessToken(String subject, String userId, LocalDateTime expiredDateTime){

        Key key = getKeyFromBase64EncodedKey(secretKeyUtil.getSecretKey());
        Date expiredDate = getExpiredDate(expiredDateTime);

        String expiredDateString = getExpiredDateString(expiredDate);
        JwtPayLoad jwtPayLoad = new JwtPayLoad(userId, expiredDateString);//payload data

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", jwtPayLoad.getUserId());
        claims.put("expiredTime", jwtPayLoad.getExpiredTime());

        return Jwts.builder()
                .subject(subject)
                .claims(claims)
                .issuedAt(new Date())
                .expiration(expiredDate)
                .signWith(key)
                .compact();
    }

    /**
     * Jwt Access Token 파싱
     *
     * @param accessToken
     * @return
     */
    public JwtPayLoad parseAccessToken(String accessToken){
        byte[] secretByte = Base64.getDecoder().decode(secretKeyUtil.getSecretKey());
        SecretKey key = Keys.hmacShaKeyFor(secretByte);
        Claims payload = Jwts.parser().verifyWith(key).build().parseSignedClaims(accessToken).getPayload();
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

    /**
     * 만료 일시 LocalDateTime을 Date 로 변환
     *
     * @param expiredDateTime
     * @return
     */
    public Date getExpiredDate(LocalDateTime expiredDateTime){
        return Date.from(expiredDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 만료 일시 Date를 String 으로 변환
     *
     * @param expiredDate
     * @return
     */
    private String getExpiredDateString(Date expiredDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(expiredDate);
    }

    /**
     * encodeSecretKey 변환
     *
     * @param base64EncodedSecretKey
     * @return
     */
    private static Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
