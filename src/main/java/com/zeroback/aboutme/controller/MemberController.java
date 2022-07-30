package com.zeroback.aboutme.controller;


import com.zeroback.aboutme.dto.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/test")
    public String memberTest(){
        return "member";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDto signupDto){
        return null;
    }
}
