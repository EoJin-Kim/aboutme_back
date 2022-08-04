package com.zeroback.aboutme.service;

import com.zeroback.aboutme.dto.request.CreateTeamDto;
import com.zeroback.aboutme.dto.request.JoinTeamDto;
import com.zeroback.aboutme.dto.response.MemberInfoDto;
import com.zeroback.aboutme.dto.response.MemberSummaryDto;
import com.zeroback.aboutme.dto.response.TeamInfoDto;
import com.zeroback.aboutme.entity.Member;
import com.zeroback.aboutme.entity.MemberTeam;
import com.zeroback.aboutme.entity.Team;
import com.zeroback.aboutme.repository.MemberRepository;
import com.zeroback.aboutme.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    public String createTeam(CreateTeamDto createTeamDto) throws Exception{

        Long memberId = createTeamDto.getMemberId();
        Member findMember = memberRepository.findById(memberId).orElseThrow();

        Team createTeam = Team.create(createTeamDto.getTeamName());
        MemberTeam createMemberTeam = MemberTeam.create(findMember, createTeam);

        teamRepository.save(createTeam);
        return "ok";

    }

    public List<TeamInfoDto> fetchTeams(Long memberId) {
        Member findMember = memberRepository.findById(memberId).get();
        ArrayList<TeamInfoDto> teamInfoList = new ArrayList<>();
        List<MemberTeam> memberTeamList = findMember.getMemberTeam();
        for (MemberTeam memberTeam : memberTeamList) {
            TeamInfoDto teamInfoDto = new TeamInfoDto();
            teamInfoDto.setTeamName(memberTeam.getTeam().getName());
            teamInfoList.add(teamInfoDto);
        }
        return teamInfoList;
    }

    public String joinTeam(JoinTeamDto joinTeamDto) throws Exception{
        Long memberId = joinTeamDto.getMemberId();
        Member findMember = memberRepository.findById(memberId).orElseThrow();

        Team team = teamRepository.findTeamByName(joinTeamDto.getTeamName()).orElseThrow();
        MemberTeam memberTeam = MemberTeam.create(findMember, team);
        teamRepository.saveMemberTeam(memberTeam);
        return "ok";
    }

    public List<MemberSummaryDto> fetchMember(String teamName)throws Exception {
        Team team = teamRepository.findTeamByName(teamName).orElseThrow();
        List<MemberTeam> memberTeamList = team.getMemberTeam();

        ArrayList<MemberSummaryDto> memberSummaryList = new ArrayList<>();
        for (MemberTeam memberTeam : memberTeamList) {
            Member member = memberTeam.getMember();
            MemberSummaryDto memberSummaryDto = new MemberSummaryDto(member.getId(), member.getName(), member.getJob());
            memberSummaryList.add(memberSummaryDto);

        }
        return memberSummaryList;
    }
}
