package com.szs.test.api;

import com.szs.test.dto.ResDTO;
import com.szs.test.util.ResUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Scrap {

    /**
     * 스크래핑
     *
     * @return
     */
    @PostMapping(value = "/szs/scrap")
    @ResponseBody
    public ResponseEntity<ResDTO> scrap(HttpServletRequest request){
        String accessToken = request.getHeader("Authorization");

        return ResUtil.success("", HttpStatus.OK);
    }

}
