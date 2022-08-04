package com.zeroback.aboutme.controller;


import com.zeroback.aboutme.dto.request.LoginDto;
import com.zeroback.aboutme.dto.request.MemberDetailRequestDto;
import com.zeroback.aboutme.dto.request.SignupDto;
import com.zeroback.aboutme.dto.response.TeamInfoDto;
import com.zeroback.aboutme.dto.response.LoginResultDto;
import com.zeroback.aboutme.dto.response.MemberDetailResponseDto;
import com.zeroback.aboutme.dto.response.ResponseDto;
import com.zeroback.aboutme.service.MemberService;
import com.zeroback.aboutme.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final TeamService teamService;

    @GetMapping("/test")
    public String memberTest(){
        return "member";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDto signupDto){
        String result = memberService.signup(signupDto);
        if (result.equals("success")) {
            ResponseDto<String> response = new ResponseDto<>("success", "ok");
            return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
        } else {
            ResponseDto<String> response = new ResponseDto<>("false", "no");
            return new ResponseEntity<ResponseDto>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        try {
            LoginResultDto loginResultDto = memberService.login(loginDto);
            ResponseDto<LoginResultDto> response = new ResponseDto<LoginResultDto>("success", loginResultDto);
            return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDto<String> response = new ResponseDto<>("false", "no");
            return new ResponseEntity<ResponseDto>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<?> getMemberInfo(@PathVariable("memberId") Long memberId) {
        try {
            MemberDetailResponseDto memberInfo = memberService.getMemberInfo(memberId);
            ResponseDto<MemberDetailResponseDto> response = new ResponseDto<MemberDetailResponseDto>("success", memberInfo);
            return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDto<String> response = new ResponseDto<>("false", "no");
            return new ResponseEntity<ResponseDto>(response, HttpStatus.BAD_REQUEST);
        }

    }
    @PatchMapping("/{memberId}")
    public ResponseEntity<?> updateMemberInfo(@PathVariable("memberId") Long memberId,@RequestBody MemberDetailRequestDto memberDetailInfo) {
        String result = memberService.updateMember(memberId, memberDetailInfo);
        if (result.equals("success")) {
            ResponseDto<String> response = new ResponseDto<>("success", "ok");
            return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
        } else {
            ResponseDto<String> response = new ResponseDto<>("false", "no");
            return new ResponseEntity<ResponseDto>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/team/{memberId}")
    public ResponseEntity<?> fetchTeams(@PathVariable("memberId") Long memberId) {
        List<TeamInfoDto> teamList = teamService.fetchTeams(memberId);
        ResponseDto<List> response = new ResponseDto<>("success", teamList);
        return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
    }
}
