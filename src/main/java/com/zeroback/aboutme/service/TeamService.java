package com.zeroback.aboutme.service;

import com.zeroback.aboutme.dto.request.CreateTeamDto;
import com.zeroback.aboutme.dto.request.JoinTeamDto;
import com.zeroback.aboutme.dto.response.MemberInfoDto;
import com.zeroback.aboutme.dto.response.MemberSummaryDto;
import com.zeroback.aboutme.dto.response.TeamInfoDto;
import com.zeroback.aboutme.dto.response.TeamTotalInfo;
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
    public List<TeamInfoDto> createTeam(CreateTeamDto createTeamDto) throws Exception{

        Long memberId = createTeamDto.getMemberId();
        Member findMember = memberRepository.findById(memberId).orElseThrow();

        Team createTeam = Team.create(createTeamDto);
        MemberTeam createMemberTeam = MemberTeam.create(findMember, createTeam);

        teamRepository.save(createTeam);

        ArrayList<TeamInfoDto> teamInfoList = new ArrayList<>();
        List<MemberTeam> memberTeamList = findMember.getMemberTeam();
        for (MemberTeam memberTeam : memberTeamList) {
            TeamInfoDto teamInfoDto = new TeamInfoDto();
            teamInfoDto.setGroupId(memberTeam.getTeam().getId());
            teamInfoDto.setTeamName(memberTeam.getTeam().getName());
            teamInfoDto.setSummary(memberTeam.getTeam().getSummary());
            teamInfoDto.setCount(memberTeam.getTeam().getMemberTeam().size());
            teamInfoList.add(teamInfoDto);
        }
        return teamInfoList;

    }

    public List<TeamInfoDto> fetchTeams(Long memberId) {
        Member findMember = memberRepository.findById(memberId).get();
        ArrayList<TeamInfoDto> teamInfoList = new ArrayList<>();
        List<MemberTeam> memberTeamList = findMember.getMemberTeam();
        for (MemberTeam memberTeam : memberTeamList) {
            TeamInfoDto teamInfoDto = new TeamInfoDto();
            teamInfoDto.setGroupId(memberTeam.getTeam().getId());
            teamInfoDto.setTeamName(memberTeam.getTeam().getName());
            teamInfoDto.setSummary(memberTeam.getTeam().getSummary());
            teamInfoDto.setCount(memberTeam.getTeam().getMemberTeam().size());
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

    public TeamTotalInfo findTeam(Long teamId) throws Exception{
        Team team = teamRepository.findById(teamId).orElseThrow();

        TeamTotalInfo teamTotalInfo = new TeamTotalInfo();

        teamTotalInfo.setGroupName(team.getName());
        teamTotalInfo.setGroupSummary(team.getSummary());

        List<MemberTeam> memberTeamList = team.getMemberTeam();
        teamTotalInfo.setCount(memberTeamList.size());
        ArrayList<MemberSummaryDto> memberSummaryList = new ArrayList<>();

        for (MemberTeam memberTeam : memberTeamList) {
            Member member = memberTeam.getMember();
            MemberSummaryDto memberSummaryDto = new MemberSummaryDto(member.getId(), member.getName(), member.getJob());
            memberSummaryList.add(memberSummaryDto);
        }

        teamTotalInfo.setMemberSummary(memberSummaryList);
        return teamTotalInfo;
    }
}
