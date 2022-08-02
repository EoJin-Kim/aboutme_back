package com.zeroback.aboutme.repository;


import com.zeroback.aboutme.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface MemberRepository extends JpaRepository<Member,Long>, MemberQueryRepository {
    Optional<Member> findMemberByEmailAndPassword(String emaail,String password);
}
