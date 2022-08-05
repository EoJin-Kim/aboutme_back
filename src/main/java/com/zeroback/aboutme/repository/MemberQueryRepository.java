package com.zeroback.aboutme.repository;

import com.zeroback.aboutme.entity.MemberInfo;


public interface MemberQueryRepository {
    public void deleteMemberTag(Long memberId);

    MemberInfo findMemberInfoById(Long memberInfoId);
}
