package com.szs.test.service;

import com.szs.test.domain.MemberEntity;
import com.szs.test.domain.MemberRepository;
import com.szs.test.dto.SignupDTO;
import com.szs.test.exception.NotSignupUserException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.szs.test.constants.Constants.userMap;

@Service
public class SignupService {

    @Resource
    private MemberRepository memberRepository;

    /**
     * 회원 가입
     *
     * @param param
     * @return
     */
    @Transactional
    public Long insertMember(SignupDTO.ReqAdd param){

        if(isValidUser(param.getName(), param.getRegNo()) == false){
            throw new NotSignupUserException("가입할 수 없는 유저 입니다.");
        }

        try{
            MemberEntity memberEntity = param.toEntity();
            memberRepository.save(memberEntity);
            return memberEntity.getId();
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 가입 가능 유저에 대한 유효성 체크
     *
     * @param name
     * @param regNo
     * @return
     */
    public boolean isValidUser(String name, String regNo){

        for(String key : userMap.keySet()){
            if(key.equals(name)){
                return userMap.get(key).equals(regNo);
            }
        }

        return false;
    }

}
