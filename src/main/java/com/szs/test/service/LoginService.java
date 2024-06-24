package com.szs.test.service;

import com.szs.test.cd.LoginStatusCd;
import com.szs.test.domain.LoginHistRepository;
import com.szs.test.domain.MemberRepositoryCustom;
import com.szs.test.dto.LoginDTO;
import com.szs.test.dto.MemberDTO;
import com.szs.test.exception.InvalidPasswordException;
import com.szs.test.exception.NotExistsDataException;
import com.szs.test.util.EncryptUtil;
import com.szs.test.util.JwtTokenUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class LoginService {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private MemberRepositoryCustom memberRepositoryCustom;

    @Resource
    private LoginHistRepository loginHistRepository;

    /**
     * 로그인
     *
     * @param param
     * @return
     */
    @Transactional
    public LoginDTO.ResSuccess login(LoginDTO.ReqLogin param){

        MemberDTO.ResGet resGet = memberRepositoryCustom.findByUserId(param.getUserId());
        if(resGet == null){
            loginHistRepository.save(param.toEntity(LoginStatusCd.INVALID_USER_ID.getStatusCd(), null, null));
            throw new NotExistsDataException("유저 정보가 존재하지 않습니다.");
        }

        String encryptPassword = EncryptUtil.sha256Encrypt(param.getPassword());
        if(resGet.getPassword().equals(encryptPassword) == false){
            loginHistRepository.save(param.toEntity(LoginStatusCd.INVALID_PASSWORD.getStatusCd(), null, null));
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }

        final int EXPIRED_TIME_FOR_MINUTE = 15;
        LocalDateTime expiredDateTime = LocalDateTime.now().plusMinutes(EXPIRED_TIME_FOR_MINUTE);
        String accessToken = jwtTokenUtil.createAccessToken("로그인 Access Token", param.getUserId(), expiredDateTime);

        loginHistRepository.save(param.toEntity(LoginStatusCd.LOGIN_SUCCESS.getStatusCd(), null, null));
        return new LoginDTO.ResSuccess(accessToken);
    }

}
