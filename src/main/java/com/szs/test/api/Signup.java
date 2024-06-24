package com.szs.test.api;

import com.szs.test.dto.ResDTO;
import com.szs.test.dto.SignupDTO;
import com.szs.test.util.ResUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Signup {

    /**
     * 회원 가입
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/szs/signup")
    @ResponseBody
    public ResponseEntity<ResDTO> signup(@RequestBody @Valid SignupDTO.ReqAdd param){
        return ResUtil.success(param, HttpStatus.CREATED);
    }

}
