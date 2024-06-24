package com.szs.test.api;

import com.szs.test.dto.ResDTO;
import com.szs.test.util.ResUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Refund {

    /**
     * 결정세액 조회
     *
     * @return
     */
    @GetMapping(value = "/szs/refund", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResDTO> refund(HttpServletRequest request){
        String accessToken = request.getHeader("Authorization");

        return ResUtil.success("", HttpStatus.OK);
    }

}
