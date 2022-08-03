package com.zeroback.aboutme.util;

import com.zeroback.aboutme.entity.Member;
import com.zeroback.aboutme.entity.MemberInfo;
import com.zeroback.aboutme.entity.MemberTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;
        public void dbInit1() {
            Member member = Member.createMember("김어진", "test@test.com", "test");
            member.setContent("안녕하세요");
            member.setJob("개발자");
            member.setPhone("010-2341-1411");
            em.persist(member);
            MemberTag.create(member,"백엔드");
            MemberTag.create(member,"프론트");
            MemberTag.create(member,"안드로이드");

            List<MemberInfo> memberInfoList = member.getMemberInfo();
            List<String> infos = Arrays.asList("요리를 잘한다", "일을 뒤로 미룬다","잘때는 건들지 말기!","ABCD","고기","해산물","부드러움","컴퓨터");
            for(int i = 0 ; i<8;i++){
                memberInfoList.get(i).setContent(infos.get(i));
            }

        }
    }
}
