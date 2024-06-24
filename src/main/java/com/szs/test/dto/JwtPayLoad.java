package com.szs.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class JwtPayLoad {

    private String userId;//아이디
    private String expiredTime;//만료 시간

}
