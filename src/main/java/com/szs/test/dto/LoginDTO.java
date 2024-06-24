package com.szs.test.dto;

import com.szs.test.domain.LoginHistEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class LoginDTO {

    @Setter
    @Getter
    public static class ReqLogin{

        @NotBlank(message = "아이디는 필수 입니다.")
        private String userId;

        @NotBlank(message = "비밀번호는 필수 입니다.")
        private String password;

        public LoginHistEntity toEntity(String loginStatusCd, String accessToken, LocalDateTime loginDt){
            return new LoginHistEntity.Builder()
                    .userId(this.userId)
                    .statusCd(loginStatusCd)
                    .accessToken(accessToken)
                    .loginDt(loginDt)
                    .build();
        }
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class ResSuccess{

        private String accessToken;
    }
}
