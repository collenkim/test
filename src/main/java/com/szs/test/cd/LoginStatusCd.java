package com.szs.test.cd;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginStatusCd {

    LOGIN_SUCCESS( "LOGIN_SUCCESS", "로그인 성공"),
    INVALID_USER_ID( "INVALID_USER_ID", "로그인 ID 불일치"),
    INVALID_PASSWORD("INVALID_PASSWORD", "비밀번호 불일치"),
    ;

    private final String statusCd;
    private final String statusNm;

}
