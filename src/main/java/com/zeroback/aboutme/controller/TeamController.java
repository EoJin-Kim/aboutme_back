package com.zeroback.aboutme.controller;


import com.zeroback.aboutme.dto.request.CreateTeamDto;
import com.zeroback.aboutme.dto.request.JoinTeamDto;
import com.zeroback.aboutme.dto.response.*;
import com.zeroback.aboutme.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/test")
    public String memberTest(){
        return "team";
    }

    // 그룹 사용자 조회
    @GetMapping("/member/{teamName}")
    public ResponseEntity<?> fetchTeams(@PathVariable("teamName") String teamName) {
        try {
            List<MemberSummaryDto> teamList = teamService.fetchMember(teamName);
            ResponseDto<List> response = new ResponseDto<>("success", teamList);
            return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDto<String> response = new ResponseDto<>("false", "no");
            return new ResponseEntity<ResponseDto>(response, HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/{groupId}")
    public ResponseEntity<?> fetchTeam(@PathVariable("groupId") Long groupId){
        try {
            TeamTotalInfo teamTotalInfo = teamService.findTeam(groupId);
            ResponseDto<TeamTotalInfo> response = new ResponseDto<>("success",teamTotalInfo);
            return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
        }catch ( Exception e){
            ResponseDto<String> response = new ResponseDto<>("false", "no");
            return new ResponseEntity<ResponseDto>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // 그룹 생성
    @PostMapping
    public ResponseEntity<?> createTeam(@RequestBody CreateTeamDto createTeamDto){
        try {
            List<TeamInfoDto> result = teamService.createTeam(createTeamDto);
            ResponseDto<List> response = new ResponseDto<>("success", result);
            return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);

        } catch (Exception e) {
            ResponseDto<String> response = new ResponseDto<>("false", "no");
            return new ResponseEntity<ResponseDto>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // 그룹 참가
    @PostMapping("/join")
    public ResponseEntity<?> joinTeam(@RequestBody JoinTeamDto joinTeamDto) {
        try {
            List<TeamInfoDto> result = teamService.joinTeam(joinTeamDto);
            ResponseDto<List> response = new ResponseDto<>("success", result);
            return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);

        } catch (Exception e) {
            ResponseDto<String> response = new ResponseDto<>("false", "no");
            return new ResponseEntity<ResponseDto>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
