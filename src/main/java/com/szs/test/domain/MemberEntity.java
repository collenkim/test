package com.szs.test.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "reg_no", nullable = false)
    private String regNo;

    protected MemberEntity() {
    }

    private MemberEntity(Builder builder) {
        this.userId = builder.userId;
        this.password = builder.password;
        this.name = builder.name;
        this.regNo = builder.regNo;
    }

    // Static inner Builder class
    public static class Builder {
        private String userId;
        private String password;
        private String name;
        private String regNo;

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder regNo(String regNo) {
            this.regNo = regNo;
            return this;
        }

        public MemberEntity build() {
            // Validate required fields
            if (userId == null || password == null || name == null || regNo == null) {
                throw new IllegalArgumentException("All fields must be provided");
            }
            return new MemberEntity(this);
        }
    }

}
