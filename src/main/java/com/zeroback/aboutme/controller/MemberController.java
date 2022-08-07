package com.zeroback.aboutme.controller;


import com.zeroback.aboutme.dto.request.LoginDto;
import com.zeroback.aboutme.dto.request.MemberDetailRequestDto;
import com.zeroback.aboutme.dto.request.SignupDto;
import com.zeroback.aboutme.dto.response.*;
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

    // 회원가입
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

    // 로그인
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

    // 사용자 정보 조회
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
    // 사용자 정보 수정
    @PatchMapping("/{memberId}")
    public ResponseEntity<?> updateMember(@PathVariable("memberId") Long memberId,@RequestBody MemberDetailRequestDto memberDetailInfo) {
        String result = memberService.updateMember(memberId, memberDetailInfo);
        if (result.equals("success")) {
            ResponseDto<String> response = new ResponseDto<>("success", "ok");
            return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
        } else {
            ResponseDto<String> response = new ResponseDto<>("false", "no");
            return new ResponseEntity<ResponseDto>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // 사용자 그룹 조회
    @GetMapping("/team/{memberId}")
    public ResponseEntity<?> fetchTeams(@PathVariable("memberId") Long memberId) {
        List<TeamInfoDto> teamList = teamService.fetchTeams(memberId);
        ResponseDto<List> response = new ResponseDto<>("success", teamList);
        return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
    }

    // 사용자 상세 정보 수정
    @PatchMapping("/memberinfo/{memberInfoId}")
    public ResponseEntity<?> updateMemberInfo(@PathVariable("memberInfoId") Long memberInfoId,@RequestBody UpdateMemberInfoDto memberInfo){
        try {
            List<MemberInfoDto> result = memberService.updateMemberInfo(memberInfoId, memberInfo);
            ResponseDto<List> response = new ResponseDto<>("success", result);
            return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
        }catch (Exception e){
            ResponseDto<String> response = new ResponseDto<>("false", "no");
            return new ResponseEntity<ResponseDto>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
