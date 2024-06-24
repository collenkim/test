package com.szs.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class MemberDTO {

    @Setter
    @Getter
    @AllArgsConstructor
    public static class ResGet{

        private Long id;
        private String userId;
        private String password;
        private String name;
        private String regNo;

    }
}
