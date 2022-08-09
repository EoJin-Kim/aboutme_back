package com.zeroback.aboutme.util;

import com.zeroback.aboutme.dto.request.CreateTeamDto;
import com.zeroback.aboutme.entity.*;
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
            Member member = createMember("김어진", "test@test.com", "안녕하세요", "개발자", "010-2341-1411", "백엔드", "프론트", "안드로이드");


            Member member2 = createMember("김아진", "test2@test.com", "안녕하세요22", "개발자22", "010-2341-141122", "백엔드22", "프론트22", "안드로이드22");
            Member member3 = createMember("김아진", "test3@test.com", "안녕하세요22", "개발자22", "010-2341-141122", "백엔드22", "프론트22", "안드로이드22");
            Member member4 = createMember("김아진", "test4@test.com", "안녕하세요22", "개발자22", "010-2341-141122", "백엔드22", "프론트22", "안드로이드22");
            Member member5 = createMember("김아진", "test5@test.com", "안녕하세요22", "개발자22", "010-2341-141122", "백엔드22", "프론트22", "안드로이드22");
            Member member6 = createMember("김아진", "test6@test.com", "안녕하세요22", "개발자22", "010-2341-141122", "백엔드22", "프론트22", "안드로이드22");
            Member member7 = createMember("김아진", "test7@test.com", "안녕하세요22", "개발자22", "010-2341-141122", "백엔드22", "프론트22", "안드로이드22");

            CreateTeamDto cooking = new CreateTeamDto(1L, "cooking", "요리합시다!");
            CreateTeamDto running = new CreateTeamDto(1L, "running", "달립시다!");
            CreateTeamDto developer = new CreateTeamDto(1L, "developer", "개발합시다!");

            Team team1 = Team.create(cooking);
            Team team2 = Team.create(running);
            Team team3 = Team.create(developer);

            MemberTeam.create(member,team1);
            MemberTeam.create(member2,team1);
            MemberTeam.create(member3,team1);
            MemberTeam.create(member4,team1);
            MemberTeam.create(member5,team1);
            MemberTeam.create(member6,team1);
            MemberTeam.create(member7,team1);

            MemberTeam.create(member,team2);
//            MemberTeam.create(member,team3);

            em.persist(team1);
            em.persist(team2);
            em.persist(team3);

        }

        private Member createMember(String 김어진, String email, String 안녕하세요, String 개발자, String phone, String 백엔드, String 프론트, String 안드로이드) {
            Member member = Member.createMember(김어진, email, "test");
            member.setContent(안녕하세요);
            member.setJob(개발자);
            member.setPhone(phone);
            em.persist(member);
            MemberTag.create(member, 백엔드);
            MemberTag.create(member, 프론트);
            MemberTag.create(member, 안드로이드);

            List<MemberInfo> memberInfoList = member.getMemberInfo();
            List<String> infos = Arrays.asList("요리를 잘한다", "일을 뒤로 미룬다","잘때는 건들지 말기!","ABCD","고기","해산물","부드러움","컴퓨터");
            createMemberInfo(memberInfoList,infos);
            return member;
        }

        private void createMemberInfo(List<MemberInfo> memberInfoList, List<String> infos) {
            for(int i = 0 ; i<8;i++){
                memberInfoList.get(i).setContent(infos.get(i));
            }
        }
    }
}
