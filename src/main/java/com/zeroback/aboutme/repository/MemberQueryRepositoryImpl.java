package com.zeroback.aboutme.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zeroback.aboutme.entity.MemberInfo;
import com.zeroback.aboutme.entity.QMemberInfo;
import lombok.RequiredArgsConstructor;

import static com.zeroback.aboutme.entity.QMemberInfo.memberInfo;
import static com.zeroback.aboutme.entity.QMemberTag.memberTag;

@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public void deleteMemberTag(Long memberId) {
        queryFactory.delete(memberTag)
                .where(memberTag.member.id.eq(memberId))
                .execute();
    }

    @Override
    public MemberInfo findMemberInfoById(Long memberInfoId) {
        MemberInfo memberInfo = queryFactory.selectFrom(QMemberInfo.memberInfo)
                .where(QMemberInfo.memberInfo.id.eq(memberInfoId))
                .fetchOne();
        return memberInfo;
    }
}
