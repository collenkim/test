package com.szs.test.api;

import com.szs.test.dto.LoginDTO;
import com.szs.test.dto.ResDTO;
import com.szs.test.util.ResUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login {

    /**
     * 로그인
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/szs/login")
    @ResponseBody
    public ResponseEntity<ResDTO> login(@RequestBody @Valid LoginDTO.ReqLogin param){
        return ResUtil.success(param, HttpStatus.OK);
    }

}
