package com.zeroback.aboutme.repository;


import com.zeroback.aboutme.entity.MemberTeam;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class TeamQueryRepositoryImpl implements TeamQueryRepository{

    private final EntityManager em;

    @Override
    public void saveMemberTeam(MemberTeam memberTeam) {
        em.persist(memberTeam);
    }
}
