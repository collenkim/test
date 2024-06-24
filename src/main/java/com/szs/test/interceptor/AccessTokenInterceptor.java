package com.szs.test.interceptor;

import com.szs.test.dto.JwtPayLoad;
import com.szs.test.util.JwtTokenUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        if (isValidAccessToken(accessToken)) {
            // AccessToken 유효성 검증 성공
            return true;
        } else {
            // AccessToken 유효성 검증 실패 시 처리
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
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
