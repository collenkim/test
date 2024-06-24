package com.szs.test.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResDTO {

    private Object data;
    private String errorCd;
    private String errorMessage;

    public ResDTO(Object data){
        this.data = data;
    }

    public ResDTO(String errorCd, String errorMessage){
        this.errorCd = errorCd;
        this.errorMessage = errorMessage;
    }

}
