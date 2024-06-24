package com.szs.test.util;

import com.szs.test.dto.ResDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResUtil {

    public static ResponseEntity<ResDTO> success(Object data, HttpStatus httpStatus){
        ResDTO response = new ResDTO(data);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<ResDTO> fail(String errorCd, String errorMessage, HttpStatus httpStatus){
        ResDTO response = new ResDTO(errorCd, errorMessage);
        return new ResponseEntity<>(response, httpStatus);
    }
}
