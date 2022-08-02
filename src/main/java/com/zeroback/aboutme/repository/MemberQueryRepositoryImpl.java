package com.zeroback.aboutme.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zeroback.aboutme.entity.QMemberTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
