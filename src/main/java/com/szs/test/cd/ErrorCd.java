package com.szs.test.cd;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCd {

    //User Error
    INVALID_PARAMETER(400, "U0001", "유효하지 않은 파라미터"),
    INVALID_PASSWORD(400, "U0002", "패스워드 불일치"),

    //Client Error
    EXPIRED_ACCESS_TOKEN(400, "C0001", "만료된 Access Token"),
    INVALID_ACCESS_TOKEN(400, "C0002", "유효하지 않은 Access Token"),
    INVALID_JSON_FORMAT(400, "C0003", "잘못된 Json Format"),
    NOT_EXISTS_DATA(400, "C0004", "데이터가 존재하지 않음"),
    NOT_SIGNUP_USER(400, "C0005", "가입할 수 없는 유저"),

    //Server Error
    INTERNAL_SERVER_ERROR(500, "S0001", "서버 에러"),
    ;

    private final int status;
    private final String errorCd;
    private final String errorMessage;

}
