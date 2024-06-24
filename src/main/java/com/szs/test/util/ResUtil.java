package com.szs.test.util;

import com.szs.test.dto.ResDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResUtil {

    /**
     * 성공 공통 응답
     *
     * @param data
     * @param httpStatus
     * @return
     */
    public static ResponseEntity<ResDTO> success(Object data, HttpStatus httpStatus){
        ResDTO response = new ResDTO(data);
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * 실패 공통 응답
     *
     * @param errorCd
     * @param errorMessage
     * @param httpStatus
     * @return
     */
    public static ResponseEntity<ResDTO> fail(String errorCd, String errorMessage, HttpStatus httpStatus){
        ResDTO response = new ResDTO(errorCd, errorMessage);
        return new ResponseEntity<>(response, httpStatus);
    }
}
