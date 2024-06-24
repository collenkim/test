package com.szs.test.domain;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.szs.test.dto.MemberDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class MemberRepositoryCustom {

    @Resource
    private JPAQueryFactory jpaQueryFactory;

    /**
     * 유저 정보 단건 조회
     *
     * @param userId
     * @return
     */
    public MemberDTO.ResGet findByUserId(String userId) {
        return jpaQueryFactory
                .select(Projections.constructor(MemberDTO.ResGet.class,
                        QMemberEntity.memberEntity.id,
                        QMemberEntity.memberEntity.userId,
                        QMemberEntity.memberEntity.password,
                        QMemberEntity.memberEntity.name,
                        QMemberEntity.memberEntity.regNo))
                .from(QMemberEntity.memberEntity)
                .where(QMemberEntity.memberEntity.userId.eq(userId))
                .fetchFirst();
    }

}
