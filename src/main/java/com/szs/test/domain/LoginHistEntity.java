package com.szs.test.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "login_hist")
public class LoginHistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "status_cd", nullable = false)
    private String statusCd;

    @Column(name = "login_dt")
    private LocalDateTime loginDt;

    protected LoginHistEntity() {
    }

    private LoginHistEntity(LoginHistEntity.Builder builder) {
        this.userId = builder.userId;
        this.accessToken = builder.accessToken;
        this.statusCd = builder.statusCd;
        this.loginDt = builder.loginDt;
    }

    // Static inner Builder class
    public static class Builder {
        private String userId;
        private String accessToken;
        private String statusCd;
        private LocalDateTime loginDt;

        public LoginHistEntity.Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public LoginHistEntity.Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public LoginHistEntity.Builder statusCd(String statusCd) {
            this.statusCd = statusCd;
            return this;
        }

        public LoginHistEntity.Builder loginDt(LocalDateTime loginDt) {
            this.loginDt = loginDt;
            return this;
        }

        public LoginHistEntity build() {
            // Validate required fields
            if (userId == null || statusCd == null) {
                throw new IllegalArgumentException("");
            }

            return new LoginHistEntity(this);
        }
    }
}
