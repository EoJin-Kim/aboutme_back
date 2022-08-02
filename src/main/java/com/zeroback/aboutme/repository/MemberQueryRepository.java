package com.zeroback.aboutme.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


public interface MemberQueryRepository {
    public void deleteMemberTag(Long memberId);
}
