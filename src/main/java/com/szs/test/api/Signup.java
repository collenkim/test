package com.szs.test.api;

import com.szs.test.dto.ResDTO;
import com.szs.test.dto.SignupDTO;
import com.szs.test.service.SignupService;
import com.szs.test.util.ResUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Signup {

    @Resource
    private SignupService signupService;

    /**
     * 회원 가입
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/szs/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResDTO> signup(@RequestBody @Valid SignupDTO.ReqAdd param){
        return ResUtil.success(signupService.insertMember(param), HttpStatus.CREATED);
    }

}
