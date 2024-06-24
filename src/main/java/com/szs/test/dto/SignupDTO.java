package com.szs.test.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class SignupDTO {

    @Setter
    @Getter
    public static class ReqAdd{

        @NotBlank(message = "아이디는 필수 입니다.")
        private String userId;

        @NotBlank(message = "비밀번호는 필수 입니다.")
        private String password;

        @NotBlank(message = "이름은 필수 입니다.")
        private String name;

        @NotBlank(message = "주민번호는 필수 입니다.")
        private String regNo;

    }

}
