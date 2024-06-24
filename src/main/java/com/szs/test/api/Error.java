package com.szs.test.api;

import com.szs.test.exception.ExpiredAccessTokenException;
import com.szs.test.exception.InvalidAccessTokenException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Error {

    /**
     * 유효하지 않은 토큰에 대한 에러 처리
     *
     */
    @GetMapping(value = "/error/access-token/invalid")
    public void invalidAccessToken() {
        throw new InvalidAccessTokenException("토큰 정보가 유효하지 않습니다");
    }

    /**
     * 만료된 토큰에 대한 에러 처리
     *
     */
    @GetMapping(value = "/error/access-token/expired")
    public void expiredAccessToken(){
        throw new ExpiredAccessTokenException("만료된 토큰 입니다.");
    }
}
