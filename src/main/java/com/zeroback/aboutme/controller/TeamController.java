package com.zeroback.aboutme.controller;


import com.zeroback.aboutme.dto.request.CreateTeamDto;
import com.zeroback.aboutme.dto.request.JoinTeamDto;
import com.zeroback.aboutme.dto.response.MemberInfoDto;
import com.zeroback.aboutme.dto.response.MemberSummaryDto;
import com.zeroback.aboutme.dto.response.TeamInfoDto;
import com.zeroback.aboutme.dto.response.ResponseDto;
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

    // 그룹 생성
    @PostMapping
    public ResponseEntity<?> createTeam(@RequestBody CreateTeamDto createTeamDto){
        try {
            String result = teamService.createTeam(createTeamDto);
            ResponseDto<String> response = new ResponseDto<>("success", "ok");
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
            String result = teamService.joinTeam(joinTeamDto);
            ResponseDto<String> response = new ResponseDto<>("success", "ok");
            return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);

        } catch (Exception e) {
            ResponseDto<String> response = new ResponseDto<>("false", "no");
            return new ResponseEntity<ResponseDto>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
