package com.zeroback.aboutme.repository;

import com.zeroback.aboutme.entity.MemberTeam;
import com.zeroback.aboutme.entity.Team;

import java.util.Optional;

public interface TeamQueryRepository {

    void saveMemberTeam(MemberTeam memberTeam);
}
