package com.szs.test.interceptor;

import com.szs.test.dto.JwtPayLoad;
import com.szs.test.exception.InvalidAccessTokenException;
import com.szs.test.util.JwtTokenUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AccessTokenInterceptor implements HandlerInterceptor {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // AccessToken을 검증하고 처리하는 로직
        String accessToken = request.getHeader("Authorization");
        if(StringUtils.isBlank(accessToken)){
            throw new InvalidAccessTokenException("Access Token 정보가 없습니다.");
        }

        if (isValidAccessToken(accessToken)) {
            // AccessToken 유효성 검증 성공
            return true;
        }

        // AccessToken 유효성 검증 실패 시 처리
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }

    /**
     * Access Token의 유효성 체크
     *
     * @param accessToken
     * @return
     */
    private boolean isValidAccessToken(String accessToken) {
        // AccessToken 유효성 검증하는 로직
        JwtPayLoad jwtPayLoad = jwtTokenUtil.parseAccessToken(accessToken);
        return jwtTokenUtil.isExpiredJwtToken(jwtPayLoad);
    }

}
