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


            List<String> tags1 = Arrays.asList("스키", "축구", "보드");
            List<String> infos1 = Arrays.asList(
                    "요리를 잘한다\n컴퓨터를 잘한다\n운동을 열심히한다!!",
                    "일을 뒤로 미룬다",
                    "잘때는 건들지 말기!",
                    "ABCD",
                    "고기",
                    "해산물",
                    "부드러움",
                    "컴퓨터"
            );
            Member member = createMember("김어진","", "test@test.com", "안녕하세요", "개발자", "010-2341-1411", tags1,infos1);

            List<String> infos2 = Arrays.asList("요리를 잘한다", "일을 뒤로 미룬다","잘때는 건들지 말기!","ABCD","고기","해산물","부드러움","컴퓨터");
            List<String> tags2 = Arrays.asList("백엔드22", "프론트22", "안드로이드22");
            Member member2 = createMember("김두한", "1.jpg","test2@test.com", "안녕하세요22", "개발자22", "010-2341-141122",tags2,infos2);
            Member member3 = createMember("사자", "2.jpg","test3@test.com", "사자입니다!!", "배우", "010-2341-141122",tags2,infos2 );
            Member member4 = createMember("팽귄", "3.jpg","test4@test.com", "안녕하세요22", "개그맨", "010-2341-141122",tags2,infos2 );
            Member member5 = createMember("호랑이","4.jpg", "test5@test.com", "안녕하세요22", "애널리스트", "010-2341-141122",tags2,infos2 );
            Member member6 = createMember("기린","5.jpg", "test6@test.com", "안녕하세요22", "치킨집 사장", "010-2341-141122",tags2,infos2 );
            Member member7 = createMember("고양이", "6.jpg","test7@test.com", "안녕하세요22", "마트 직원", "010-2341-141122",tags2,infos2 );



            CreateTeamDto cooking = new CreateTeamDto(
                    1L,
                    "cooking",
                    "요리합시다!\n" +
                            "매주 수요일, 목요일 오후 8시\n" +
                            "한식 요리의 정점을 배우고 가자!!",
                    "1234"
            );
            CreateTeamDto running = new CreateTeamDto(1L, "running", "달립시다!","1234");
            CreateTeamDto developer = new CreateTeamDto(10L, "developer", "개발합시다!","1234");

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
            MemberTeam.create(member2,team3);

            em.persist(team1);
            em.persist(team2);
            em.persist(team3);

        }

        private Member createMember(String name, String image , String email, String content, String job, String phone, List<String> tags,List<String> infos) {
            Member member = Member.createMember(name, email, "test");

            member.setImage(image);
            member.setContent(content);
            member.setJob(job);
            member.setPhone(phone);
            em.persist(member);
            for (String tag : tags) {
                MemberTag.create(member, tag);
            }

            List<MemberInfo> memberInfoList = member.getMemberInfo();

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
