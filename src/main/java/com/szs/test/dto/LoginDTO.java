package com.szs.test.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class LoginDTO {

    @Setter
    @Getter
    public static class ReqLogin{

        @NotBlank(message = "아이디는 필수 입니다.")
        private String userId;

        @NotBlank(message = "비밀번호는 필수 입니다.")
        private String password;

    }
}
